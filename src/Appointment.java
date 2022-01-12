public class Appointment {

    private Customer customer;
    private String status, startDate, endDate;
    private HomeAppliance appliance;

    public Appointment(){
        this.customer = new Customer();
        this.status = "unknown";
        this.startDate = "00/00/0000";
        this.endDate = "00/00/0000";
        this.appliance = new HomeAppliance();
    }

    public Appointment(Customer customer, String status, String startDate, String endDate, HomeAppliance appliance) {
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.appliance = appliance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public HomeAppliance getAppliance() {
        return appliance;
    }

    public void setAppliance(HomeAppliance appliance) {
        this.appliance = appliance;
    }


}
