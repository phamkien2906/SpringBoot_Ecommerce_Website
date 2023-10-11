package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    @Lob
//    Trong Spring Boot, @Lob là một annotation được sử dụng để đánh dấu một trường dữ liệu trong entity là
//    một trường clob (Clob là một kiểu dữ liệu được sử dụng để lưu trữ các chuỗi dữ liệu lớn trong cơ sở dữ liệu,
//    trong khi blob được sử dụng để lưu trữ dữ liệu nhị phân lớn)

//    Khi sử dụng @Lob, Spring Boot sẽ xử lý trường đó một cách đặc biệt, được lưu trữ riêng biệt
//    với các trường dữ liệu thông thường trong entity. Điều này cho phép lưu trữ và truy xuất các dữ
//    liệu lớn một cách hiệu quả trong cơ sở dữ liệu.
    @Column(columnDefinition = "MEDIUMBLOB")
//     Kiểu dữ liệu MEDIUMBLOB trong MySQL được sử dụng để lưu trữ đối tượng nhị phân lớn,
//     bao gồm các loại dữ liệu như hình ảnh, video, âm thanh và các file đa phương tiện khác.
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "admins_roles",
            joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;
}
