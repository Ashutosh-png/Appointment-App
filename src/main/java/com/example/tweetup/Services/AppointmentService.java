package com.example.tweetup.Services;

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


	


}
