package com.example.attendance.Controller;

import com.example.attendance.Entity.AttendanceDTO;
import com.example.attendance.Entity.StudentStatus;
import com.example.attendance.Service.AttendanceService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    public static class AttendancePayload
    {
        public List<StudentStatus> students;
        public String markedById;
        public Date classDate;
        public int classId;

        @Override
        public String toString() {
            return "AttendancePayload{" +
                    "students=" + students +
                    ", markedById='" + markedById + '\'' +
                    ", classDate=" + classDate +
                    ", classId=" + classId +
                    '}';
        }
    }
    ;
   @PostMapping("/addAttendanceList")
   public ResponseEntity<List<AttendanceDTO>> addAttendanceList(@RequestBody AttendancePayload payload)  {
       System.out.println("payload: " + payload.toString());
       attendanceService.addAttendanceList(payload.students, payload.classId, payload.classDate, payload.markedById);
       return ResponseEntity.ok().build();
   }


    @GetMapping("/getAll")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendances() {
        ResponseEntity<List<AttendanceDTO>> response = attendanceService.getAllAttendance();
        return ResponseEntity.ok(response.getBody());
    }



   @PutMapping("/updateAttendance")
    public ResponseEntity<Void> updateAttendance(@RequestParam String studentId, @RequestParam Integer classId, @RequestParam String status)
   {
       System.out.println("studentId:"+studentId+" classId:"+classId+" status:"+status);
       attendanceService.updateAttendance(studentId,classId,status);
       return ResponseEntity.ok().build();
   }

    @GetMapping("/getByStudentId/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getByStudentId(@PathVariable String studentId) {
        ResponseEntity<List<AttendanceDTO>> response = attendanceService.getByStudentId(studentId);
        return ResponseEntity.ok(response.getBody());
    }




}
