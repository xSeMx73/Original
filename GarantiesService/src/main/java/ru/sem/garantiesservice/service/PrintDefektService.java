package ru.sem.garantiesservice.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.sem.garantiesservice.model.GarantRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrintDefektService {

 //   DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public byte[] createDefekt(GarantRequest request) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Map<String, String> placeholders = createPlaceholders(request);
        ClassPathResource resource = new ClassPathResource("defektovka.docx");
        try (XWPFDocument document = new XWPFDocument(new FileInputStream(resource.getFile()))) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String paragraphText = paragraph.getParagraphText();
                for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                    if (paragraphText.contains(entry.getKey())) {
                        // Заменяем метку на соответствующее значение
                        paragraphText = paragraphText.replace(entry.getKey(), entry.getValue());

                        // Создаем список для удаляемых 'run'
                        List<XWPFRun> runsToRemove = new ArrayList<>(paragraph.getRuns());

                        // Удаляем старый текст
                        for (XWPFRun run : runsToRemove) {
                            paragraph.removeRun(0);
                        }

                        // Создание нового текста
                        XWPFRun newRun = paragraph.createRun();
                        newRun.setText(paragraphText);
                    }
                }
            }
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    private Map<String, String> createPlaceholders(GarantRequest request) {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("{{артикулЗапчасти}}", request.getPartArticle());
        placeholders.put("{{брендЗапчасти}}", request.getPartBrand());
        placeholders.put("{{названиеЗапчасти}}", request.getPartName());
        placeholders.put("{{датаСнятия}}", String.valueOf(request.getDateRemovePart()));
        placeholders.put("{{брендАвтомобиля}}",request.getTransportBrand());
        placeholders.put("{{модельАвтомобиля}}",request.getTransportModel());
        placeholders.put("{{VIN}}",request.getVin());
        placeholders.put("{{пробег}}", String.valueOf(request.getMileageEnd()));
        placeholders.put("{{номер}}", request.getGosNumber());
        placeholders.put("{{описание}}", request.getFaultDescription());

        return placeholders;
    }

}
