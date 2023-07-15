package com.infotel.eshop.mapper;

import com.infotel.eshop.dto.AddressDto;
import com.infotel.eshop.dto.CustomerDto;
import com.infotel.eshop.dto.LoginDto;
import com.infotel.eshop.dto.MerchantDto;
import com.infotel.eshop.dto.RegisterDto;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Merchant;

public interface UserMapper {
	
	
	static CustomerDto customerToCustomerDto(Customer cust) {
        CustomerDto dto = new CustomerDto();
        dto.setUsername(cust.getUsername());
        dto.setFirstName(cust.getFirstName());
        dto.setLastName(cust.getLastName());
        dto.setTitle(cust.getTitle());
        dto.setPhone(cust.getPhone());
        
        if(cust.getAddress() != null) {
        	AddressDto addrDto = new AddressDto();
        	addrDto.setCity(cust.getAddress().getCity());
        	addrDto.setZip(cust.getAddress().getPostcode());
        	addrDto.setStreet(cust.getAddress().getStreet());
        	dto.setAddress(addrDto);
        }
        return dto;
    }
	
	
	static MerchantDto MerchantToMerchantDto(Merchant merchant) {
		MerchantDto dto = new MerchantDto();
	    dto.setUsername( merchant.getUsername());
		dto.setFirstName( merchant.getFirstName());
		dto.setLastName( merchant.getLastName());
		
		return dto;
	}

    static Customer registerDtoToCustomer(RegisterDto dto) {
        Customer cust = new Customer();
        cust.setUsername(dto.getUsername());
        cust.setPassword(dto.getPassword());
        cust.setTitle(dto.getTitle());
        cust.setFirstName(dto.getFirstName());
        cust.setLastName(dto.getLastName());

        return cust;
    }

    static Customer customerDtoToCustomer(CustomerDto dto) {
        Customer cust = new Customer();
        cust.setUsername(dto.getUsername());

        return cust;
    }

    static LoginDto credentialsToLoginDto(String username, String password) {
        LoginDto dto = new LoginDto();
        dto.setUsername(username);
        dto.setPassword(password);

        return dto;
    }
}

