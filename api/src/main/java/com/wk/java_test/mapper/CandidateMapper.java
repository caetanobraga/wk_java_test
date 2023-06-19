package com.wk.java_test.mapper;

import com.wk.java_test.domain.BloodType;
import com.wk.java_test.domain.Candidate;
import com.wk.java_test.utils.GetBirthDateService;
import com.wk.java_test.utils.GetBloodTypeService;
import org.json.JSONObject;

public class CandidateMapper {

    public static Candidate toEntity(JSONObject data){

        Candidate entity = new Candidate();
        entity.setName(data.getString("nome"));
        entity.setCpf(data.getString("cpf"));
        entity.setRg(data.getString("rg"));
        entity.setBirthDate(new GetBirthDateService().getDate(data.getString("data_nasc")));
        entity.setSex(data.getString("sexo"));
        entity.setMother(data.getString("mae"));
        entity.setFather(data.getString("pai"));
        entity.setEmail(data.getString("email"));
        entity.setAddress(AddressMapper.toEntity(data));
        entity.setPhone(data.getString("telefone_fixo"));
        entity.setCellPhone(data.getString("celular"));
        entity.setHeight(data.getDouble("altura"));
        entity.setWeight(data.getInt("peso"));
        entity.setBloodType(new GetBloodTypeService().getType(data.getString("tipo_sanguineo")));

        return entity;
    }
}
