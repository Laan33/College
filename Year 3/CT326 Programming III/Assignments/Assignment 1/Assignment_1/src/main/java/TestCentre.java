public class TestCentre {
    private String centreName;
    private String centreAddress;

    public TestCentre(String centreName, String centreAddress) {
        //checking if centre name or address is null
        if (centreName == null) {
            throw new IllegalArgumentException("Centre name cannot be null");
        } else if (centreAddress == null) {
            throw new IllegalArgumentException("Centre address cannot be null");
        }
        this.centreName = centreName;
        this.centreAddress = centreAddress;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCentreAddress() {
        return centreAddress;
    }

    public void setCentreAddress(String centreAddress) {
        this.centreAddress = centreAddress;
    }

    @Override
    public String toString() {
        return "Centre: " + centreName + " Address: " + centreAddress;
    }
}
