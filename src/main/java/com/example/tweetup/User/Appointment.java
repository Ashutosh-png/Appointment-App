package com.example.tweetup.User;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	    private LocalDateTime appointmentTime;
	    private Long doctorId;
	    private Long patientId;
	    private String docname;
	    private String patname;
	    
		public String getDocname() {
			return docname;
		}
		public void setDocname(String docname) {
			this.docname = docname;
		}
		public String getPatname() {
			return patname;
		}
		public void setPatname(String patname) {
			this.patname = patname;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public LocalDateTime getAppointmentTime() {
			return appointmentTime;
		}
		public void setAppointmentTime(LocalDateTime appointmentTime) {
			this.appointmentTime = appointmentTime;
		}
		public Long getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(Long doctorId) {
			this.doctorId = doctorId;
		}
		public Long getPatientId() {
			return patientId;
		}
		public void setPatientId(Long patientId) {
			this.patientId = patientId;
		}
	    
	    
	    
	

}
