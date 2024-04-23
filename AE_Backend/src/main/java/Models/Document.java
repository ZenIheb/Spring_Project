package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Models.Assetes.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

	@Id
	@SequenceGenerator(
			name = "document_sequence" ,
			sequenceName = "document_sequence",
			allocationSize = 1
	)
	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    				generator = "document_sequence" )
	
    private Long id;

	@NonNull
    @Column(name = "title", nullable = false, unique = true)
    private String title;
	
    @Column(name = "description", nullable = true)
    private String description;
	
    @Column(name = "fileName", nullable = false, unique = true)
    private String fileName;

    @Column(name = "Type", nullable = false)
    private FileType type;
    
    @ManyToOne
 	@JoinColumn(name="user_id",referencedColumnName = "id")
 	private User user ;
    
    @Transient
    public MultipartFile file ;
    
 
	
}
