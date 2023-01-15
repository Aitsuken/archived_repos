package kuraido.midka.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity(name = "Customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_email_unique", columnNames = "email_address")
        }
)
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_sequence"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name= "fisrt_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name= "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name= "email_address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String emailAddress;

    @Column(
            name = "job_title"
    )
    private String jobTitle;

    @Column(
            name= "mobile_phone",
            //nullable = false,
            //columnDefinition = "TEXT",
            unique = true
    )
    private String mobilePhone;

    @Column(
            name = "city"
    )
    private String city;

    @Column(
            name= "web_page",
            unique = true
    )
    private String webPage;


    public Customer(Long id, String firstName, String lastName, String emailAddress,
                    String jobTitle, String mobilePhone, String city, String webPage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.mobilePhone = mobilePhone;
        this.city = city;
        this.webPage = webPage;
    }

    public Customer(String firstName, String lastName, String emailAddress,
                    String jobTitle, String mobilePhone, String city, String webPage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.mobilePhone = mobilePhone;
        this.city = city;
        this.webPage = webPage;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    /*    Create a Database "Customers" with "customers"
    table with firstName, lastName, emailAddress, jobTitle,
    mobilePhone, city, webPage fields*/

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", city='" + city + '\'' +
                ", webPage='" + webPage + '\'' +
                '}';
    }
}
