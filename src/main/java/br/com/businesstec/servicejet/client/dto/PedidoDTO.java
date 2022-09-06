package br.com.businesstec.servicejet.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDTO extends EntityDTO {

    private LocalDateTime expirationDate;
    private LocalDateTime paymentDate;
    private String paymentFormId;
    private String paymentFormDescription;
    private int idShipping;
    private int idShippingHub;
    private String shippingCompany;
    private String shippingMode;
    private String shippingRegister;
    private String typeCustomer;
    private LocalDateTime birthDate;
    private String gender;
    private String phone1;
    private String phone2;
    private String group;
    private int b2bB2c;
    private String externalId;
    private String idPaymentType;
    private String idAdminCard;
    private String cardAuthorizationCode;
    private String cardNsu;
    private String orderNotes;
    private String marketPlaceNumberOrder;
    private int marketPlaceID;
    private String marketPlaceName;
    private LocalDateTime marketPlaceDateCreated;
    private String marketPlaceStore;
    private Integer idSeller;
    private int crossDocking;
    private String billNumber;
    private String proofOfSale;
    private boolean originApp;
    private OrderZapcommerceDTO orderZapcommerce;
    private int idCustomer;
    private double totalShipping;
    private double totalDiscount;
    private AddressDTO address;
    private AddressDTO billingAddress;
    private String email;
    private int idPaymentBrand;
    private String codeBank;
    private String nameBank;
    private String agency;
    private String checkingAccount;
    private String creditCardFlag;
    private int numberOfInstallments;
    private double valueOfInstallment;
    private List<OrderItemDTO> orderItems;
    private String nameCustomer;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    private String rg_ie;
    private String nameShipping;
    private String deliveryShipping;
    private double totalItens;
    private double totalInstallment;
    private List<HistoryListOrderStatusDTO> historyListOrderStatus;
    private String customerExternalId;
    private String paymentLink;
    private String metaData;
    private double totalShoppingVoucher;
    private int idTypePayment;
    private OrderPaymentDTO orderPayment;
    private String codigoExternoFrete;
    private int deliveryTime;
    private Boolean usefulDay;
    private String nameCarrying;
    private String trackingLink;
    private String recurrentCodePlan;
    private String recurrentSelectedTime;
    private double interestValue;
    private String descricaoDetalhada;
    private int idOrder;
    private LocalDateTime dateOrder;
    private double total;
    private String namePaymentMethodGateway;
    private String nameStatus;
    private int orderType;

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentFormId() {
        return paymentFormId;
    }

    public void setPaymentFormId(String paymentFormId) {
        this.paymentFormId = paymentFormId;
    }

    public String getPaymentFormDescription() {
        return paymentFormDescription;
    }

    public void setPaymentFormDescription(String paymentFormDescription) {
        this.paymentFormDescription = paymentFormDescription;
    }

    public int getIdShipping() {
        return idShipping;
    }

    public void setIdShipping(int idShipping) {
        this.idShipping = idShipping;
    }

    public int getIdShippingHub() {
        return idShippingHub;
    }

    public void setIdShippingHub(int idShippingHub) {
        this.idShippingHub = idShippingHub;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMode(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public String getShippingRegister() {
        return shippingRegister;
    }

    public void setShippingRegister(String shippingRegister) {
        this.shippingRegister = shippingRegister;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getB2bB2c() {
        return b2bB2c;
    }

    public void setB2bB2c(int b2bB2c) {
        this.b2bB2c = b2bB2c;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getIdPaymentType() {
        return idPaymentType;
    }

    public void setIdPaymentType(String idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    public String getIdAdminCard() {
        return idAdminCard;
    }

    public void setIdAdminCard(String idAdminCard) {
        this.idAdminCard = idAdminCard;
    }

    public String getCardAuthorizationCode() {
        return cardAuthorizationCode;
    }

    public void setCardAuthorizationCode(String cardAuthorizationCode) {
        this.cardAuthorizationCode = cardAuthorizationCode;
    }

    public String getCardNsu() {
        return cardNsu;
    }

    public void setCardNsu(String cardNsu) {
        this.cardNsu = cardNsu;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getMarketPlaceNumberOrder() {
        return marketPlaceNumberOrder;
    }

    public void setMarketPlaceNumberOrder(String marketPlaceNumberOrder) {
        this.marketPlaceNumberOrder = marketPlaceNumberOrder;
    }

    public int getMarketPlaceID() {
        return marketPlaceID;
    }

    public void setMarketPlaceID(int marketPlaceID) {
        this.marketPlaceID = marketPlaceID;
    }

    public String getMarketPlaceName() {
        return marketPlaceName;
    }

    public void setMarketPlaceName(String marketPlaceName) {
        this.marketPlaceName = marketPlaceName;
    }

    public LocalDateTime getMarketPlaceDateCreated() {
        return marketPlaceDateCreated;
    }

    public void setMarketPlaceDateCreated(LocalDateTime marketPlaceDateCreated) {
        this.marketPlaceDateCreated = marketPlaceDateCreated;
    }

    public String getMarketPlaceStore() {
        return marketPlaceStore;
    }

    public void setMarketPlaceStore(String marketPlaceStore) {
        this.marketPlaceStore = marketPlaceStore;
    }

    public Integer getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Integer idSeller) {
        this.idSeller = idSeller;
    }

    public int getCrossDocking() {
        return crossDocking;
    }

    public void setCrossDocking(int crossDocking) {
        this.crossDocking = crossDocking;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getProofOfSale() {
        return proofOfSale;
    }

    public void setProofOfSale(String proofOfSale) {
        this.proofOfSale = proofOfSale;
    }

    public boolean isOriginApp() {
        return originApp;
    }

    public void setOriginApp(boolean originApp) {
        this.originApp = originApp;
    }

    public OrderZapcommerceDTO getOrderZapcommerce() {
        return orderZapcommerce;
    }

    public void setOrderZapcommerce(OrderZapcommerceDTO orderZapcommerce) {
        this.orderZapcommerce = orderZapcommerce;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public double getTotalShipping() {
        return totalShipping;
    }

    public void setTotalShipping(double totalShipping) {
        this.totalShipping = totalShipping;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPaymentBrand() {
        return idPaymentBrand;
    }

    public void setIdPaymentBrand(int idPaymentBrand) {
        this.idPaymentBrand = idPaymentBrand;
    }

    public String getCodeBank() {
        return codeBank;
    }

    public void setCodeBank(String codeBank) {
        this.codeBank = codeBank;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(String checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public String getCreditCardFlag() {
        return creditCardFlag;
    }

    public void setCreditCardFlag(String creditCardFlag) {
        this.creditCardFlag = creditCardFlag;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public double getValueOfInstallment() {
        return valueOfInstallment;
    }

    public void setValueOfInstallment(double valueOfInstallment) {
        this.valueOfInstallment = valueOfInstallment;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRg_ie() {
        return rg_ie;
    }

    public void setRg_ie(String rg_ie) {
        this.rg_ie = rg_ie;
    }

    public String getNameShipping() {
        return nameShipping;
    }

    public void setNameShipping(String nameShipping) {
        this.nameShipping = nameShipping;
    }

    public String getDeliveryShipping() {
        return deliveryShipping;
    }

    public void setDeliveryShipping(String deliveryShipping) {
        this.deliveryShipping = deliveryShipping;
    }

    public double getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(double totalItens) {
        this.totalItens = totalItens;
    }

    public double getTotalInstallment() {
        return totalInstallment;
    }

    public void setTotalInstallment(double totalInstallment) {
        this.totalInstallment = totalInstallment;
    }

    public List<HistoryListOrderStatusDTO> getHistoryListOrderStatus() {
        return historyListOrderStatus;
    }

    public void setHistoryListOrderStatus(List<HistoryListOrderStatusDTO> historyListOrderStatus) {
        this.historyListOrderStatus = historyListOrderStatus;
    }

    public String getCustomerExternalId() {
        return customerExternalId;
    }

    public void setCustomerExternalId(String customerExternalId) {
        this.customerExternalId = customerExternalId;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public double getTotalShoppingVoucher() {
        return totalShoppingVoucher;
    }

    public void setTotalShoppingVoucher(double totalShoppingVoucher) {
        this.totalShoppingVoucher = totalShoppingVoucher;
    }

    public int getIdTypePayment() {
        return idTypePayment;
    }

    public void setIdTypePayment(int idTypePayment) {
        this.idTypePayment = idTypePayment;
    }

    public OrderPaymentDTO getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(OrderPaymentDTO orderPayment) {
        this.orderPayment = orderPayment;
    }

    public String getCodigoExternoFrete() {
        return codigoExternoFrete;
    }

    public void setCodigoExternoFrete(String codigoExternoFrete) {
        this.codigoExternoFrete = codigoExternoFrete;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getUsefulDay() {
        return usefulDay;
    }

    public void setUsefulDay(Boolean usefulDay) {
        this.usefulDay = usefulDay;
    }

    public String getNameCarrying() {
        return nameCarrying;
    }

    public void setNameCarrying(String nameCarrying) {
        this.nameCarrying = nameCarrying;
    }

    public String getTrackingLink() {
        return trackingLink;
    }

    public void setTrackingLink(String trackingLink) {
        this.trackingLink = trackingLink;
    }

    public String getRecurrentCodePlan() {
        return recurrentCodePlan;
    }

    public void setRecurrentCodePlan(String recurrentCodePlan) {
        this.recurrentCodePlan = recurrentCodePlan;
    }

    public String getRecurrentSelectedTime() {
        return recurrentSelectedTime;
    }

    public void setRecurrentSelectedTime(String recurrentSelectedTime) {
        this.recurrentSelectedTime = recurrentSelectedTime;
    }

    public double getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(double interestValue) {
        this.interestValue = interestValue;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNamePaymentMethodGateway() {
        return namePaymentMethodGateway;
    }

    public void setNamePaymentMethodGateway(String namePaymentMethodGateway) {
        this.namePaymentMethodGateway = namePaymentMethodGateway;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
