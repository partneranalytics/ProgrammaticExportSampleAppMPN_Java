package com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import com.microsoft.partneranalytics.sampleprogrammaticaccessMPN.helpers.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("ProgrammaticExportSampleAppMPN")
public class ProgrammaticExportSampleAppControllerMPN {

    @Autowired
    private Environment env;

    @Autowired
    private TokenGenerator tokenGenrator;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private ExportDownloader exportDownloader;

    Logger _logger = LoggerFactory.getLogger(ProgrammaticExportSampleAppControllerMPN.class);

    @RequestMapping(path = "datasets", method = RequestMethod.GET)
    public ResponseEntity GetDatasets(){

        String accessToken = null;
        try{
            accessToken = tokenGenrator.GenerateToken(env);
        }catch(Exception e){
            _logger.error(e.getMessage());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        String baseUrl = env.getProperty("ProgrammaticExportMPN.ApiEndpointUrl");
        String datasetPath = env.getProperty("ProgrammaticExportMPN.DatasetsPath");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + datasetPath, HttpMethod.GET, request, String.class);
        ResponseMapper<ScheduledDatasetObject> mapper = new ResponseMapper<ScheduledDatasetObject>();

        return new ResponseEntity(mapper.mapResponse(response, "value"), HttpStatus.OK) ;
    }

    @RequestMapping(path = "query", method = RequestMethod.GET)
    public ResponseEntity GetQueries(){

        String accessToken = null;
        try{
            accessToken = tokenGenrator.GenerateToken(env);
        }catch(Exception e){
            _logger.error(e.getMessage());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        String baseUrl = env.getProperty("ProgrammaticExportMPN.ApiEndpointUrl");
        String queryPath = env.getProperty("ProgrammaticExportMPN.QueryPath");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + queryPath, HttpMethod.GET, request, String.class);
        ResponseMapper<ScheduledQueriesObject> mapper = new ResponseMapper<ScheduledQueriesObject>();

        return new ResponseEntity(mapper.mapResponse(response, "value"), HttpStatus.OK);
    }

    @RequestMapping(path = "tryquery", method = RequestMethod.GET)
    public ResponseEntity TryQuery(@RequestParam String export_query){

        String accessToken = null;
        try{
            accessToken = tokenGenrator.GenerateToken(env);
        }catch(Exception e){
            _logger.error(e.getMessage());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        String baseUrl = env.getProperty("ProgrammaticExportMPN.ApiEndpointUrl");
        String datasetPath = env.getProperty("ProgrammaticExportMPN.QueryPath") + "/testQueryResult?exportQuery=" + export_query;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + datasetPath, HttpMethod.GET, request, String.class);
        ResponseMapper<Object> mapper = new ResponseMapper<Object>();

        return new ResponseEntity(mapper.mapResponse(response, "value"), HttpStatus.OK);
    }

    @RequestMapping(path = "query", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity CreateQuery(@RequestParam String query, @RequestParam String query_name){

        if(commonUtils.isNullOrBlank(query) || commonUtils.isNullOrBlank(query_name)){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        String accessToken = null;
        try{
            accessToken = tokenGenrator.GenerateToken(env);
        }catch(Exception e){
            _logger.error(e.getMessage());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        ScheduledQueriesInputObject payload = new ScheduledQueriesInputObject(query_name, query);

        String baseUrl = env.getProperty("ProgrammaticExportMPN.ApiEndpointUrl");
        String queryPath = env.getProperty("ProgrammaticExportMPN.QueryPath");

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<ScheduledQueriesInputObject> requestEntity =new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = template.exchange(baseUrl + queryPath, HttpMethod.POST, requestEntity, String.class);

        ResponseMapper<ScheduledQueriesObject> mapper = new ResponseMapper<ScheduledQueriesObject>();

        return new ResponseEntity(mapper.mapFirstResponseField(response, "value", "queryId"), HttpStatus.OK);
    }

    @RequestMapping(path = "report", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity CreateReport(@RequestParam String query_id, @RequestParam String report_name, @RequestParam String report_start_name, @RequestParam int recurrence_interval, @RequestParam String recurrence_count){

        if(commonUtils.isNullOrBlank(query_id) || commonUtils.isNullOrBlank(report_name)){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        String accessToken = null;
        try{
            accessToken = tokenGenrator.GenerateToken(env);
        }catch(Exception e){
            _logger.error(e.getMessage());
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

        if(commonUtils.isNullOrBlank(report_start_name)){
            Instant i = Instant.now().plusSeconds(5400L);
            Date myDate = Date.from(i);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy'-'MM'-'dd HH':'mm':'ss'Z'");
            report_start_name = formatter.format(myDate);
        }

        ScheduledReportCreateInputObject payload = new ScheduledReportCreateInputObject(
                report_name,
                query_id,
                report_start_name,
                recurrence_interval,
                Integer.parseInt(recurrence_count));

        String baseUrl = env.getProperty("ProgrammaticExportMPN.ApiEndpointUrl");
        String queryPath = env.getProperty("ProgrammaticExportMPN.ReportPath");

        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<ScheduledReportCreateInputObject> requestEntity =new HttpEntity<ScheduledReportCreateInputObject>(payload, headers);
        ResponseEntity<String> response = template.exchange(baseUrl + queryPath, HttpMethod.POST, requestEntity, String.class);

        ResponseMapper<ScheduledReportObject> mapper = new ResponseMapper<ScheduledReportObject>();

        return new ResponseEntity(mapper.mapFirstResponseField(response, "value", "reportId"), HttpStatus.OK);
    }

    @RequestMapping(path = "reportready/{reportId}", method = RequestMethod.POST)
    public ResponseEntity ReportReady(@PathVariable String reportId){
        exportDownloader.DownloadReport(reportId);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}