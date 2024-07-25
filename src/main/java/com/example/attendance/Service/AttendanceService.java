package com.example.attendance.Service;

import com.example.attendance.Entity.AttendanceDTO;
import com.example.attendance.Entity.StudentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${dbConnector.host}")
    private String dbConnectorHost;

    @Value("${dbConnector.addAttendanceList}")
    private String addAttendanceListEndpoint;

    @Value("${dbConnector.updateAttendnce}")
    private String updateAttendanceUrl;
    @Value("${dbConnector.getByStudentId}")
    private String getByStudentIdUrl;
    @Value("${dbConnector.getAllAttendance}")
    private String getAll;

    public AttendanceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


public ResponseEntity<List<AttendanceDTO>> addAttendanceList(List<StudentStatus> studentList, int classId, Date classDate, String markedById){
    String url = dbConnectorHost + addAttendanceListEndpoint;
    List<AttendanceDTO> attendanceList = new ArrayList<>();
    for (StudentStatus studentStatus : studentList) {
        AttendanceDTO currentAttendance = new AttendanceDTO();
        currentAttendance.setAttendanceId(null);
        currentAttendance.setClassId(classId);
        currentAttendance.setClassDate(classDate);
        currentAttendance.setMarkedById(markedById);
        currentAttendance.setStatus(studentStatus.getStatus());
        currentAttendance.setStudentId(studentStatus.getStudent_id());
        attendanceList.add(currentAttendance);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");
    System.out.println("AttendanceList "+attendanceList.toString());

    HttpEntity<List<AttendanceDTO>> requestEntity = new HttpEntity<>(attendanceList, headers);

    // שימוש ב-Type Reference כדי לקבוע את סוג התשובה שמתקבל
    ResponseEntity<List<AttendanceDTO>> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            new ParameterizedTypeReference<List<AttendanceDTO>>() {}
    );
    System.out.println("response is: " + response.getStatusCode());
    return response;
}



        public ResponseEntity<String> updateAttendance(@RequestParam String studentId, @RequestParam Integer classId, @RequestParam String status)
        {
            String url = UriComponentsBuilder.fromHttpUrl(dbConnectorHost + updateAttendanceUrl)
                    .queryParam("studentId", studentId)
                    .queryParam("classId", classId)
                    .queryParam("status", status)
                    .toUriString();

            // הגדרת הכותרות לבקשה
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // יצירת HttpEntity ללא גוף (כי אנחנו לא שולחים גוף)
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // שליחת הבקשה וקבלת התגובה
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

            return response;
        }





    public ResponseEntity<List<AttendanceDTO>> getByStudentId(String studentId) {
        String url = dbConnectorHost + getByStudentIdUrl+studentId;

        // Set headers if needed
        HttpHeaders headers = new HttpHeaders();

        // Make GET request and capture the response
        ResponseEntity<List<AttendanceDTO>> response = restTemplate.exchange(
                url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<AttendanceDTO>>() {});

        return response;
    }

    public ResponseEntity<List<AttendanceDTO>> getAllAttendance(){
        String url = dbConnectorHost + getAll;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<AttendanceDTO>> entity = new HttpEntity<>(headers);
        ResponseEntity<List<AttendanceDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AttendanceDTO>>() {
        });

        return response;
    }

}
