package org.cau.shoppingmall.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotBlank(message = "아이디는 필수로 입력해야 합니다.")
    @Size(min = 5, max = 20, message = "아이디는 5자 ~ 20자 사이로 작성해 주십시오")
    private String userId;

    @Size(min = 8, max = 20 ,message="비밀번호는 8자리 이상 20자리 이하로 영문자와 숫자 특수기호를 포함해야합니다." )
    @Pattern(regexp =  "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message="비밀번호는 8자리 이상 20자리 이하로 영문자와 숫자 특수기호를 포함해야합니다." )
    private String password;

    private String confirmPassword;

    @NotBlank(message = "이름은 필수로 입력해야합니다.")
    @Size(max = 10, message = "이름은 10자를 넘길 수 없습니다.")
    private String userName;


    @NotBlank(message = "생년월일을 정확하게 입력해 주십시오.")
    @Size(min = 4 ,max = 4 , message = "연도는 4자리로 입력하여 주십시오.")
    private String birthdayYear;

    @NotBlank(message = "생년월일을 정확하게 입력해 주십시오.")
    private String birthdayMonth;

    @NotBlank(message = "생년월일을 정확하게 입력해 주십시오.")
    private String birthdayDay;

    @NotBlank(message = "이메일을 정확하게 입력해 주십시오.")
    private String email;

    @NotBlank(message = "이메일을 정확하게 입력해 주십시오.")
    private String emailAfterAt;


    @Size( min = 5, max = 5 , message="우편번호는 5자리이어야 합니다.")
    private String addressCode;

    @Size( max = 256, message="주소는 256자를 넘을 수 없습니다.")
    private String address;

    @Size (max = 256 , message = "상세 주소는 256자를 넘을 수 없습니다.")
    private String addressDetails;

    @Size( min = 11, max = 11 , message = "휴대폰 번호를 정확하게 입력해주세요")
    private String phoneNumber;

    private boolean smsAgreement;

}
