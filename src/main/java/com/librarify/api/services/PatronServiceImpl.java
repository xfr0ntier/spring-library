package com.librarify.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.librarify.api.common.AppUtils;
import com.librarify.api.dtos.PatronDTO;
import com.librarify.api.entities.Patron;
import com.librarify.api.repositories.interfaces.PatronRepository;
import com.librarify.api.services.interfaces.PatronService;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;
    private final AppUtils appUtils;

    public PatronServiceImpl(PatronRepository patronRepository, AppUtils appUtils) {
        this.patronRepository = patronRepository;
        this.appUtils = appUtils;
    }

    @Override
    public Patron createPatron(PatronDTO patronDTO) {
        Patron patron = new Patron(patronDTO.getName(), patronDTO.getPhone());
        return this.patronRepository.save(patron);
    }

    @Override
    public List<Patron> getAllPatrons() {
        return this.patronRepository.findAll();
    }

    @Override
    public Patron getPatronById(UUID id) {
        Optional<Patron> res = this.patronRepository.findById(id);

        if (res.isPresent()) {
            return res.get();
        }

        // TODO: throw Patron not found

        return null;
    }

    @Override
    public Patron updatePatronById(UUID id, PatronDTO patronDTO) {
        Optional<Patron> res = this.patronRepository.findById(id);

        if (res.isPresent()) {
            Patron patron = res.get();

            patron.setName(patronDTO.getName());
            patron.setPhone(patronDTO.getPhone());
            patron.setEmail(patronDTO.getEmail());
            patron.setAddress(patronDTO.getAddress());
            patron.setUpdatedAt(this.appUtils.convertLocalDateTimeToTimestamp(LocalDateTime.now()));

            return this.patronRepository.save(patron);
        }

        // TODO: throw Patron not found
        return null;
    }

    @Override
    public void deletePatronById(UUID id) {
        Optional<Patron> res = this.patronRepository.findById(id);

        if (res.isPresent()) {
            this.patronRepository.deleteById(id);
        }

        // TODO: throw Patron not found
    }

}
