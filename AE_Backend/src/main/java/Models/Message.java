package Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {
	@Id
	@SequenceGenerator(
			name = "Message_sequence" ,
			sequenceName = "Message_sequence",
			allocationSize = 1
	)
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    				generator = "Message_sequence" )
	
    private Long id;
	
	@NonNull
    @Column(name = "Contenu", nullable = false, unique = false)
	private String Contenu ;
	
	@NonNull
	@Column(name = "Date", nullable = false, unique = false)
	private LocalDate date ;
	
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user ;
	
	 @Column(name = "discussion_id")
	 private Long discussion;

	
	
}
