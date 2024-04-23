package Models;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Discussion {

	@Id
	@SequenceGenerator(
			name = "Discussion_sequence" ,
			sequenceName = "Discussion_sequence",
			allocationSize = 1
	)
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    				generator = "Discussion_sequence" )
	
    private Long id;
	
	@NonNull
    @Column(name = "Title", nullable = false, unique = true)
	private String Title ;
	
	@NonNull
	@OneToMany(mappedBy = "discussion")
	private Set<Message> Messages = new TreeSet<>((m1, m2) -> m1.getDate().compareTo(m2.getDate())) ;
	
}
