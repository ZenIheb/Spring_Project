package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import Models.Document;
import Models.User;
import Models.Assetes.CustomFile;
import Repositories.DocumentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    
    
    private final String FOLDER_PATH = "C:\\Users\\IHE\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\AE_Backend\\src\\main\\java\\Models\\FILE_STORAGE\\" ;

    public void saveDocument(Document document) throws IOException {
    	Optional<Document> docs = documentRepository.findDocumentByTitle(document.getTitle());
		if(docs.isPresent()) throw new IllegalStateException("Title taken") ;
        document.getFile().transferTo(new File(FOLDER_PATH+document.getFileName()));
        documentRepository.save(document);
    }

    public Document getDocumentById(Long id) throws IOException {
        Document document = documentRepository.findById(id).orElse(null);
        if(document != null) {
        	byte[] file = Files.readAllBytes(new File(FOLDER_PATH+document.getFileName()).toPath());
        	MultipartFile NewFile = new CustomFile(file,document.getFileName());
        	document.setFile(NewFile);
        }
        return document ;
    }
	public List<Document> getDocumentByUser(User user) {
		return documentRepository.findDocumentByUser(user.getId());
	}

	public List<Document> getDocuments() {
		return documentRepository.findAll();
	}

	@Transactional
	public Document updateDocument(Long documentId, String title, String description) {
		Document doc = documentRepository.findById(documentId)
				.orElseThrow(() -> new IllegalStateException("Document not found with ID: " + documentId));
		
		if(title != null && !title.isEmpty()) {
			Optional<Document> docs = documentRepository.findDocumentByTitle(title);
			if(docs.isPresent()) throw new IllegalStateException("Title taken") ;
			doc.setTitle(title);
		}
		
		if(description != null) {
			doc.setDescription(description);
		}
		documentRepository.save(doc) ;
		return doc ;
	}

	public boolean deleteDocument(Long docId) {
		if(documentRepository.existsById(docId)) {
			Document doc = documentRepository.findById(docId).orElseThrow(() -> new IllegalStateException("Document not found with ID: " + docId));
			
			try {
				Files.delete(Paths.get(FOLDER_PATH+doc.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			documentRepository.deleteById(docId);
			return true ;
		}else {
			throw new IllegalStateException("Document with id "+docId+" does not exists");
		}
	}


}
