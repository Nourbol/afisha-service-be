package kz.edu.astanait.afisha.mapper;

import kz.edu.astanait.afisha.domain.Image;
import kz.edu.astanait.afisha.domain.ImageMetadata;
import kz.edu.astanait.afisha.entity.ImageEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
public class ImageMapper {

    public ImageEntity mapToImageEntity(MultipartFile file) throws IOException {
        final ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setContent(file.getBytes());
        return image;
    }

    public ImageMetadata mapToImageMetadata(ImageEntity image) {
        return new ImageMetadata(image.getId(), image.getName(), image.getType());
    }

    public Image mapToImage(ImageEntity image) {
        return new Image(image.getId(), image.getName(), image.getType(), image.getContent());
    }
}
