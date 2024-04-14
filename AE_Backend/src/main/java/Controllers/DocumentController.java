package Controllers ; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Models.Document;
import Models.User;
import Models.Assetes.FileType;
import Services.DocumentService;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("server/documents")
@AllArgsConstructor
public class DocumentController {

	
    @Autowired
    private final DocumentService documentservice;
    
    
    @GetMapping
    public List<Document> getDocuments(){
    	return documentservice.getDocuments() ;
    }
    
    @GetMapping(path = "{id}")
    public Document getDocument(@PathVariable Long id) throws IOException {
		return documentservice.getDocumentById(id);
    }
      
    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file,
										            @RequestParam("title") String title,
										            @RequestParam("description") String description,
										            @RequestParam("user") User user,
										            @RequestParam("type") FileType type) {

			try {
				Document document = new Document(null,title, description,
												file.hashCode()+"_"+file.getOriginalFilename(),
												type  ,user,file);
				
				documentservice.saveDocument(document);
				return ResponseEntity.ok("Document uploaded successfully.");
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Failed to upload document: " + e.getMessage());
		}
	}
    
    
    
    @PutMapping("/update/{id}")
    public Document updateDocument(@PathVariable("id") Long DocumentId,
												 @RequestParam(required = false) String title,
												 @RequestParam(required = false) String description){
    	return documentservice.updateDocument(DocumentId,title,description);
    }
    
    
    
    @DeleteMapping("/delete/{id}")
    public boolean deleteDocument(@PathVariable("id") Long docId){
		return documentservice.deleteDocument(docId);
    	
    }

}
