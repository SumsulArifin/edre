package com.tkgroupbd.pusti.api.services.mastersettings.organogram;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.SalesOfficer;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.sales.SalesOfficerRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.organogram.SalesOfficerRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SalesOfficerServiceImpl implements SalesOfficerService {

    @Autowired
    private SalesOfficerRepository salesOfficerRepository;

    @Override
    public MessageResponse createSalesOfficer(SalesOfficerRequest salesOfficerRequest) {

        SalesOfficer salesOfficer = new SalesOfficer();

        salesOfficer.setName(salesOfficerRequest.getName());
        salesOfficer.setDob(salesOfficerRequest.getDob());
        salesOfficer.setBloodGroup(salesOfficerRequest.getBloodGroup());
        salesOfficer.setDistrictId(salesOfficerRequest.getDistrictId());
        salesOfficer.setEmail(salesOfficerRequest.getEmail());
        salesOfficer.setCompanyId(salesOfficerRequest.getCompanyId());
        salesOfficer.setPustiOfficer(salesOfficerRequest.isPustiOfficer());
        salesOfficer.setJoiningDate(salesOfficerRequest.getJoiningDate());
        salesOfficer.setDesignationId(salesOfficerRequest.getDistrictId());
        salesOfficer.setBasicSalary(salesOfficerRequest.getBasicSalary());
        salesOfficer.setHouseRent(salesOfficerRequest.getHouseRent());
        salesOfficer.setMedicalAllowance(salesOfficerRequest.getMedicalAllowance());
        salesOfficer.setOtherAllowance(salesOfficerRequest.getOtherAllowance());
        salesOfficer.setTravellingDailyAllowance(salesOfficerRequest.getTravellingDailyAllowance());
        salesOfficer.setPhoneBill(salesOfficerRequest.getPhoneBill());
        salesOfficer.setMeetingTravellingAllowance(salesOfficerRequest.getMeetingTravellingAllowance());
        salesOfficer.setMeetingJoiningAllowance(salesOfficerRequest.getMeetingJoiningAllowance());
        salesOfficer.setMobileNumber(salesOfficerRequest.getMobileNumber());
        salesOfficer.setSscPassingYear(salesOfficerRequest.getSscPassingYear());
        salesOfficer.setHighestDegreeId(salesOfficerRequest.getHighestDegreeId());
        salesOfficer.setBankId(salesOfficerRequest.getBankId());
        salesOfficer.setBankAccountNumber(salesOfficerRequest.getBankAccountNumber());
        salesOfficer.setContributionPercentage(salesOfficerRequest.getContributionPercentage());
        salesOfficer.setPcId(salesOfficerRequest.getPcId());
        salesOfficer.setEcName(salesOfficerRequest.getEcName());
        salesOfficer.setEcPhone(salesOfficerRequest.getEcPhone());
        salesOfficer.setEcRelation(salesOfficerRequest.getEcRelation());
        salesOfficer.setDistributor(salesOfficerRequest.getDistributor());
        salesOfficerRepository.save(salesOfficer);

        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public List<SalesOfficer> getAllSalesOfficers() {
        return salesOfficerRepository.findAll();
    }

    @Override
    public SalesOfficer findSalesOfficerById(int salesOfficerId) {
        return salesOfficerRepository.findById(salesOfficerId).get();
    }

    @Override
    public Optional<SalesOfficer> updateSalesOfficer(int salesOfficerId, SalesOfficerRequest salesOfficerequest) {
        Optional<SalesOfficer> salesOfficer = salesOfficerRepository.findById(salesOfficerId);
        if (salesOfficer.isEmpty()) {
            throw new ResourceNotFoundException("Assigned_Sales_Officer", "salesOfficerId", salesOfficerId);
        } else
            salesOfficer.get().setStatus(salesOfficerequest.isStatus());
        ;
        salesOfficerRepository.save(salesOfficer.get());
        return salesOfficer;
    }

    @Override
    public void deleteSalesOfficerById(int salesOfficerId) {
        salesOfficerRepository.deleteById(salesOfficerId);
    }

    @Override
    public Optional<SalesOfficer> updateSalesOfficerStatus(int salesOfficerId, SalesOfficerRequest salesOfficerequest) {
        Optional<SalesOfficer> salesOfficer = salesOfficerRepository.findById(salesOfficerId);
        if (salesOfficer.isEmpty()) {
            throw new ResourceNotFoundException("Assigned_Sales_Officer", "salesOfficerId", salesOfficerId);
        } else
            salesOfficer.get().setStatus(salesOfficerequest.isStatus());
        ;
        salesOfficerRepository.save(salesOfficer.get());
        return salesOfficer;
    }

    @Override
    public Page<SalesOfficer> findSalesOfficersByPagination(int offset, int pageSize) {
        Page<SalesOfficer> salesOfficers = salesOfficerRepository.findAll(PageRequest.of(offset, pageSize));
        return salesOfficers;
    }
}
