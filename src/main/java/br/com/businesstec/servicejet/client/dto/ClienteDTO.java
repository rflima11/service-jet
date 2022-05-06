package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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
    @JsonProperty("address")
    private List<AddressDTO> address;
    @JsonProperty("externalId")
    private String externalId;

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(Integer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResale() {
        return resale;
    }

    public void setResale(String resale) {
        this.resale = resale;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(PhoneDTO phone) {
        this.phone = phone;
    }

    public List<AddressDTO> getAdress() {
        return address;
    }

    public void setAdress(List<AddressDTO> adress) {
        this.address = adress;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
