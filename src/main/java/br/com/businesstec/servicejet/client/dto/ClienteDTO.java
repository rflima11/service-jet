package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteDTO extends EntityDTO {

    @JsonProperty("idCustomer")
    private Long idCustomer;
    @JsonProperty("name")
    private String name;
    @JsonProperty("typeCustomer")
    private Integer typeCustomer;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("rg_ie")
    private String rg;
    @JsonProperty("profession")
    private String profession;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("maritalStatus")
    private String maritalStatus;
    @JsonProperty("url")
    private String url;
    @JsonProperty("resale")
    private String resale;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("dateBirth")
    private String dateBirth;
    @JsonProperty("status")
    private String status;
    @JsonProperty("block")
    private boolean block;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("phone")
    private PhoneDTO phone;
    @JsonProperty("adress")
    private AddressDTO adress;
    @JsonProperty("emailCategory")
    private EmailCategoryDTO emailCategory;
    @JsonProperty("externalId")
    private String externalId;



//    {
//        "idQueue": 0,
//            "actionType": "string",
//            "entity": {
//        "fantasyName": "string",
//                "typeCustomer": "fisica",
//                "cpf_cnpj": "string",
//                "rg_ie": "string",
//                "profession": "string",
//                "contact": "string",
//                "gender": "masculino",
//                "maritalStatus": "casado",
//                "url": "string",
//                "resale": "Yes",
//                "ip": "string",
//                "dateBirth": "2017-08-23T19:47:16.006Z",
//                "status": "Active",
//                "block": true,
//                "user": {
//            "email": "string"
//        },
//        "address": [
//        {
//            "idAddress": 0,
//                "streetAddress": "string",
//                "number": "string",
//                "complement": "string",
//                "neighbourhood": "string",
//                "city": "string",
//                "state": "string",
//                "zipCode": "string",
//                "country": "string",
//                "addressType": "Entrega",
//                "identification": "string",
//                "receiver": "string",
//                "referencePoint": "string",
//                "commercialResidentialAddress": "Residencial"
//        }
//            ],
//        "emailInfoCategory": [
//        {
//            "idEmailInfoCategory": 0,
//                "name": "string"
//        }
//            ],
//        "phone": {
//            "phone1": "string",
//                    "phone2": "string",
//                    "phoneAdditional": "string"
//        },
//        "idCustomer": 0,
//                "name": "string",
//                "metadata": "string"
//    }
//    }
}
