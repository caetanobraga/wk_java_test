package com.wk.java_test.mapper;

import com.wk.java_test.domain.Address;
import com.wk.java_test.domain.Candidate;
import org.json.JSONObject;

public class AddressMapper {

    public static Address toEntity(JSONObject data) {
        Address address = new Address();
        address.setCep(data.getString("cep"));
        address.setAddress(data.getString("endereco"));
        address.setNumber(data.getInt("numero"));
        address.setNeighborhood(data.getString("bairro"));
        address.setCity(data.getString("cidade"));
        address.setState(data.getString("estado"));
        return address;
    }
}