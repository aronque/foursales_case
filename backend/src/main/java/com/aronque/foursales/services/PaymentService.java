package com.aronque.foursales.services;

import com.aronque.foursales.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.sql.SQLException;

@Service
public interface PaymentService {

    void confirm(String orderId, String userId) throws ResourceNotFoundException;
}
