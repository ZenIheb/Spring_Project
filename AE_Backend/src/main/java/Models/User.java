package Models;


import javax.persistence.*;

import Models.Assetes.UserRole;
import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	@Id
	@SequenceGenerator(
			name = "user_sequence" ,
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    				generator = "user_sequence" )
	
    private Long id;

	@NonNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

	@NonNull
    @Column(name = "password", nullable = false)
    private String password;

	@NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

	@NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;
	
}

