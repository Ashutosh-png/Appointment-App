package com.example.tweetup.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tweetup.User.Appointment;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long>{

	List<Appointment> findByPatientId(Long patientId);

	List<Appointment> findByDoctorId(Long id);

}
