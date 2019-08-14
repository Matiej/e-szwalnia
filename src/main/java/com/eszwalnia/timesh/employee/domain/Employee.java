package com.eszwalnia.timesh.employee.domain;

import com.eszwalnia.timesh.authUser.domain.AuthUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    private Long id;
    @Column(name = "EPL_NAME")
    private String name;
    @Column(name = "EPL_LAST_NAME")
    private String lastName;
    @Column(name = "EPL_MAIL")
    private String email;
    @Column(name = "EPL_PHONE_NO")
    private String phoneNo;
    @Column(name = "EPL_WORK_START_DATE")
    private LocalDate workStartDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EPL_AUH_ID")
    private AuthUser authUser;
}
