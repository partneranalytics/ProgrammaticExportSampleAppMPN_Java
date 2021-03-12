package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

@Service
public class ExportDownloader {

    @Autowired
    private Environment env;

    @Autowired
    private TokenGenerator tokenGenrator;

    @Autowired
    private CommonUtils commonUtils;

    ExecutorService pool = Executors.newFixedThreadPool(10);

    Logger _logger = LoggerFactory.getLogger(ExportDownloader.class);

    public void DownloadReport(String reportId){
        pool.submit(new DownloadReport(reportId, tokenGenrator, env, _logger));
    }

    private static class DownloadReport implements Runnable {

        private final String reportId;
        private final TokenGenerator tokenGenrator;
        private final Environment env;
        Logger _logger;

        public DownloadReport(String reportId, TokenGenerator tokenGenrator, Environment env, Logger logger) {
            this.reportId = reportId;
            this.tokenGenrator = tokenGenrator;
            this.env = env;
            this._logger = logger;
        }

        @Override
        public void run() {

            String accessToken = null;
            try {
                accessToken = tokenGenrator.GenerateToken(env);
            } catch (Exception e) {
                _logger.error("Auth failure for report ID:" + reportId);
                return;
            }

            String baseUrl = env.getProperty("ProgrammaticExport.ApiEndpointUrl");
            String executionPath = env.getProperty("ProgrammaticExport.ReportExecutionPath");

            if(executionPath == null){
                _logger.error("Report execution path not provided");
                return;
            }

            String datasetPath = String.format(executionPath, reportId);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            HttpEntity<?> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = null;
            try {
                response = restTemplate.exchange(baseUrl + datasetPath, HttpMethod.GET, request, String.class);
            } catch (Exception e) {
                _logger.error("Failure in HTTP GET request to get latest Report Link for reportId" + reportId);
            }

            ResponseMapper<ScheduledReportExecutionObject> mapper = new ResponseMapper<ScheduledReportExecutionObject>();
            String reportAccessUrl = mapper.mapFirstResponseField(response, "value", "reportAccessSecureLink");
            String reportDirectory = System.getProperty("user.dir");

            try {
                String reportDir = reportDirectory + File.separator +  "DownloadedReports";
                Files.createDirectories(Paths.get(reportDir));
                this.downloadFile(reportAccessUrl, reportDir,"report" + System.currentTimeMillis() + ".csv");
            } catch (IOException e) {
                _logger.error(e.getMessage());
            }
        }

        public void downloadFile(String fileURL, String saveDir, String writeFileName)
                throws IOException {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                InputStream inputStream = httpConn.getInputStream();
                GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                String saveFilePath = saveDir + File.separator + writeFileName;

                try {
                    File targetFile = new File(saveFilePath);
                    boolean fileCreated = targetFile.createNewFile();
                    if(fileCreated){
                        FileOutputStream outputStream = new FileOutputStream(targetFile, false);

                        int bytesRead = -1;
                        byte[] buffer = new byte[4096];
                        while ((bytesRead = gzipInputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }

                        outputStream.close();
                    }else{
                        _logger.error("Unable to create file in the specified location");
                    }
                    gzipInputStream.close();
                    inputStream.close();

                }catch (Exception e){
                    _logger.error(e.getMessage());
                }
            } else {
                _logger.error("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();
        }
    }
}