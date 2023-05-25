package com.example.tweetup.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tweetup.Repository.AppointmentRepo;
import com.example.tweetup.User.Appointment;
@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepo repo;

	public void save(Appointment appointment) {
		
		if(isAppointmentTimeOverlapping(appointment)) {
			 throw new IllegalArgumentException("Appointment time overlaps with existing appointments.");
		}
		
		
		repo.save(appointment);
		
	}
	
	
	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
	    return repo.findByPatientId(patientId);
	}
	
	public List<Appointment> showall(){
		return repo.findAll();
	}


	public List<Appointment> getAppointmentsByDoctorId(Long id) {
		// TODO Auto-generated method stub
		return repo.findByDoctorId(id);
	}
	
	public void deleteAppointment(Appointment appointment) {
		repo.delete(appointment);
	}


	public Appointment findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}
	
	
	 private boolean isAppointmentTimeOverlapping(Appointment newAppointment) {
	        List<Appointment> existingAppointments = repo.findByDoctorId(newAppointment.getDoctorId());

	        for (Appointment appointment : existingAppointments) {
	            LocalDateTime existingStartTime = appointment.getAppointmentTime();
	            LocalDateTime existingEndTime = existingStartTime.plusHours(1);

	            LocalDateTime newStartTime = newAppointment.getAppointmentTime();
	            LocalDateTime newEndTime = newStartTime.plusHours(1);

	            // Check for overlap condition
	            if (newStartTime.isBefore(existingEndTime) && existingStartTime.isBefore(newEndTime)) {
	                return true; // Overlapping appointment found
	            }
	        }

	        return false; // No overlapping appointments found
	    }



	


}
