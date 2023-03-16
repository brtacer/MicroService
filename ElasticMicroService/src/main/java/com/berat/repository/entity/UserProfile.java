package com.berat.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "userprofile")
public class UserProfile extends BaseEntity{
    @Id
    String id; //uuid
    Long userprofileid; //UserProfile sınıfındaki id
    Long authid;
    String username;
    String email;
    String ad;
    String adres;
    String telefon;
    String avatar;
}
