package com.example.bntu.util;

import com.example.bntu.dto.ExtractParamsDto;
import com.example.bntu.models.Faculty;
import com.example.bntu.models.Student;
import com.example.bntu.models.enums.LessonType;
import com.example.bntu.services.StudentService;
import com.example.bntu.services.FacultyService;
import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;
import com.spire.doc.formatting.ParagraphFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WordCreator {

    private final StudentService studentService;
    private final FacultyService facultyService;

    public void createDoc(long studentId, ExtractParamsDto extractParamsDto) {
        // create a Word document
        Document document = new Document();

        // Add a section
        Section section = document.addSection();

        //настройка полей
        section.getPageSetup().getMargins().setTop(28.6f);
        section.getPageSetup().getMargins().setBottom(28.6f);
        section.getPageSetup().getMargins().setLeft(28.6f);
        section.getPageSetup().getMargins().setRight(28.6f);

        Student student = studentService.getStudentByIdWithFullInfo(studentId);
//        Faculty faculty  = facultyService.getFacultyById(facu);

        // Customize a paragraph style
        ParagraphStyle style1 = new ParagraphStyle(document);
        ParagraphStyle style2 = new ParagraphStyle(document);

        //СТИЛИ
        // стиль 1
        style1.setName("Style1");
        style1.getCharacterFormat().setFontName("Times New Roman");
        style1.getCharacterFormat().setFontSize(11f);
        document.getStyles().add(style1);

        // стиль 2
        style2.setName("Style2");
        style2.getCharacterFormat().setFontName("Times New Roman");
        style2.getCharacterFormat().setFontSize(14f);
        style2.getCharacterFormat().setBold(true);
        document.getStyles().add(style2);

        //номер диплома
        Paragraph nomer_diploma = section.addParagraph();
        nomer_diploma.appendText("Приложение к диплому А № "
                + extractParamsDto.getExtractNumber());
        nomer_diploma.applyStyle("Style1");
        ParagraphFormat format = nomer_diploma.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Right);

        Paragraph vipiska = section.addParagraph();
        vipiska.appendText("ВЫПИСКА");
        vipiska.applyStyle("Style2");
        format = vipiska.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Center);

        Paragraph vipiska2 = section.addParagraph();
        vipiska2.appendText("из зачетно-экзаменационной ведомости \n(без диплома недействительна)");
        vipiska2.applyStyle("Style1");
        format = vipiska2.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Center);

        //имя студента
        Paragraph name = section.addParagraph();
        name.appendText(student.getLastName().toUpperCase() + " "
                + student.getFirstName().toUpperCase() + " " +
                student.getMiddleName().toUpperCase());
        name.applyStyle("Style2");
        format = name.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Center);

        // Adding paragraph 1
        Paragraph para_1 = section.addParagraph();
        para_1.appendText(
                "за время обучения в Белорусском государственном техническом университете на факультете информационных " +
                        "технологий и робототехники с "+extractParamsDto.getEducationTerm()+" " +
                        "по специальности "+student.getGroup().getSpecialization().getSpecialtyCode()+" «"+student.getGroup().getSpecialization().getSpecialtyName()+"»"
                        + "и специализации "+student.getGroup().getSpecialization().getSpecialtyIndex()+" «"+student.getGroup().getSpecialization().getDirectionName()+"»"
                        + "изучил дисциплины и показал следующие знания:");
        para_1.applyStyle("Style1");
        format = para_1.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Justify);
//        style.getCharacterFormat().setFontSize(11f);


        // Iteration for white spaces
        for (int i = 0;
             i < section.getParagraphs().getCount(); i++) {

            // Automatically add whitespaces
            // to every paragraph in the file
            section.getParagraphs()
                    .get(i)
                    .getFormat()
                    .setAfterAutoSpacing(true);
        }

        //Define the data for table
        String[] header = {"Названия изученных и аттестованых учебных дисциплин", "Общее количество академических часов", "Отметки, зачеты"};
        ArrayList<ArrayList<String>> data = new ArrayList<>(0);
        ArrayList<ArrayList<String>> data_2 = new ArrayList<>(0);
        ArrayList<ArrayList<String>> data_3 = new ArrayList<>(0);
        HashMap<String, ArrayList<String>> heap = new HashMap<>();
        HashMap<String, ArrayList<String>> heap2 = new HashMap<>();
        HashMap<String, ArrayList<String>> heap3 = new HashMap<>();
        String courseWork = "Курсовая работа по дисциплине: ";
        String courseProject = "Курсовой проект по дисциплине: ";
        for (var lessonMarks : student.getLessonMarks()) {
            String key = lessonMarks.getLesson().getLessonName().getName();

            if (lessonMarks.getLesson().getLessonType() == LessonType.EXAM ||
                    lessonMarks.getLesson().getLessonType() == LessonType.TEST) {
                ArrayList<String> prev = heap.get(key);
                if (prev != null) {
                    prev.set(0, String.valueOf(Integer.valueOf(prev.get(0))
                            + Integer.valueOf(lessonMarks.getLesson().getHours())));
                    prev.set(1, prev.get(1) + ", " + getMarkWithTextStr(lessonMarks.getMark()));
                    heap.put(key, prev);
                } else {
                    ArrayList<String> hourMark = new ArrayList<>();
                    hourMark.add(lessonMarks.getLesson().getHours());
                    hourMark.add(getMarkWithTextStr(lessonMarks.getMark()));
                    heap.put(key, hourMark);
                }
            } else {
                if (lessonMarks.getLesson().getLessonType() == LessonType.COURSE_WORK) {
                    key = courseWork + key;
                    ArrayList<String> prev = heap2.get(key);
                    if (prev != null) {
                        prev.set(0, String.valueOf(Integer.valueOf(prev.get(0))
                                + Integer.valueOf(lessonMarks.getLesson().getHours())));
                        prev.set(1, prev.get(1) + ", " + getMarkWithTextStr(lessonMarks.getMark()));
                        heap2.put(key, prev);
                    } else {
                        ArrayList<String> hourMark = new ArrayList<>();
                        hourMark.add(lessonMarks.getLesson().getHours());
                        hourMark.add(getMarkWithTextStr(lessonMarks.getMark()));
                        heap2.put(key, hourMark);
                    }
                }
                if (lessonMarks.getLesson().getLessonType() == LessonType.COURSE_PROJECT) {
                    key = courseProject + key;
                    ArrayList<String> prev = heap2.get(key);
                    if (prev != null) {
                        prev.set(0, String.valueOf(Integer.valueOf(prev.get(0))
                                + Integer.valueOf(lessonMarks.getLesson().getHours())));
                        prev.set(1, prev.get(1) + ", " + getMarkWithTextStr(lessonMarks.getMark()));
                        heap2.put(key, prev);
                    } else {
                        ArrayList<String> hourMark = new ArrayList<>();
                        hourMark.add(lessonMarks.getLesson().getHours());
                        hourMark.add(getMarkWithTextStr(lessonMarks.getMark()));
                        heap2.put(key, hourMark);
                    }
                }
                if (lessonMarks.getLesson().getLessonType() == LessonType.PRACTICE) {
                    ArrayList<String> prev = heap3.get(key);
                    if (prev != null) {
                        prev.set(0, String.valueOf(Integer.valueOf(prev.get(0))
                                + Integer.valueOf(lessonMarks.getLesson().getHours())));
                        prev.set(1, prev.get(1) + ", " + getMarkWithTextStr(lessonMarks.getMark()));
                        heap3.put(key, prev);
                    } else {
                        ArrayList<String> hourMark = new ArrayList<>();
                        hourMark.add(lessonMarks.getLesson().getHours());
                        hourMark.add(getMarkWithTextStr(lessonMarks.getMark()));
                        heap3.put(key, hourMark);
                    }
                }
            }
        }
        for (var pair : heap.entrySet()) {
            ArrayList line = new ArrayList<>(){};
            line.add(pair.getKey());
            line.add(pair.getValue().get(0));
            line.add(pair.getValue().get(1));
            data.add(line);
        }
        for (var pair : heap2.entrySet()) {
            ArrayList line = new ArrayList<>(){};
            line.add(pair.getKey());
            line.add(pair.getValue().get(0));
            line.add(pair.getValue().get(1));
            data_2.add(line);
        }
        for (var pair : heap3.entrySet()) {
            ArrayList line = new ArrayList<>(){};
            line.add(pair.getKey());
            line.add(pair.getValue().get(0));
            line.add(pair.getValue().get(1));
            data_3.add(line);
        }

        //Add a table
        Table table = section.addTable(true);
        table.setColumnWidth(new float[]{260f, 145f, 145f});
        table.resetCells(data.size() + 1, header.length);

        //Set the first row as table header
        TableRow row = table.getRows().get(0);
        row.isHeader(true);
        row.setHeight(30);
        row.setHeightType(TableRowHeightType.Exactly);
        row.getRowFormat().setBackColor(Color.white);
        for (int i = 0; i < header.length; i++) {
            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //Add data to the rest of rows
        for (int r = 0; r < data.size(); r++) {
            TableRow dataRow = table.getRows().get(r + 1);
            dataRow.setHeight(20);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            dataRow.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data.get(r).size(); c++) {
                dataRow.getCells().get(c).getCellFormat().
                        setVerticalAlignment(VerticalAlignment.Middle);
                dataRow.getCells().get(c).addParagraph().
                        appendText(data.get(r).get(c).toString());
            }
        }

        Paragraph otstup1 = section.addParagraph();
        otstup1.appendText(
                " ");
        otstup1.applyStyle("Style1");

        String[] header_2 = {"Названия учебных дисциплин по выполненным и аттестованным курсовым проектам (работам)", "Общее количество академических часов", "Отметки"};


        //Add a table
        Table table_2 = section.addTable(true);
        table_2.setColumnWidth(new float[]{260f, 145f, 145f});
        table_2.resetCells(data_2.size() + 1, header_2.length);

        //Set the first row as table header
        TableRow row_2 = table_2.getRows().get(0);
        row_2.isHeader(true);
        row_2.setHeight(30);
        row_2.setHeightType(TableRowHeightType.Exactly);
        row_2.getRowFormat().setBackColor(Color.white);
        for (int i = 0; i < header_2.length; i++) {
            row_2.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row_2.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header_2[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //Add data to the rest of rows
        for (int r = 0; r < data_2.size(); r++) {
            TableRow dataRow_2 = table_2.getRows().get(r + 1);
            dataRow_2.setHeight(20);
            dataRow_2.setHeightType(TableRowHeightType.Exactly);
            dataRow_2.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data_2.get(r).size(); c++) {
                dataRow_2.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRow_2.getCells().get(c).addParagraph().appendText(data_2.get(r).get(c));
            }
        }
        Paragraph para_3 = section.addParagraph();
        para_3.appendText(
                " ");
        para_3.getFormat().setBeforeSpacing(10);

        String[] header_3 = {"Названия  пройденных и аттестованных практик", "Общее количество академических часов", "Отметки"};

        //Add a table
        Table table_3 = section.addTable(true);
        table_3.setColumnWidth(new float[]{260f, 145f, 145f});
        table_3.resetCells(data_3.size() + 1, header_3.length);

        //Set the first row as table header
        TableRow row_3 = table_3.getRows().get(0);
        row_3.isHeader(true);
        row_3.setHeight(30);
        row_3.setHeightType(TableRowHeightType.Exactly);
        row_3.getRowFormat().setBackColor(Color.white);
        for (int i = 0; i < header_3.length; i++) {
            row_3.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row_3.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange txtRange = p.appendText(header_3[i]);
            txtRange.getCharacterFormat().setBold(true);
        }

        //Add data to the rest of rows
        for (int r = 0; r < data_3.size(); r++) {
            TableRow dataRow_3 = table_3.getRows().get(r + 1);
            dataRow_3.setHeight(20);
            dataRow_3.setHeightType(TableRowHeightType.Exactly);
            dataRow_3.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data_3.get(r).size(); c++) {
                dataRow_3.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                dataRow_3.getCells().get(c).addParagraph().appendText(data_3.get(r).get(c));
            }
        }

        Paragraph para_4 = section.addParagraph();
        para_4.appendText(
                "Выполнил дипломный проект ("+student.getDiploma().getHours()+" з.е.) на тему «"+student.getDiploma().getTopic()+"»\nи защитил с отметкой _____");
        para_4.applyStyle("Style1");
        format = para_4.getFormat();
        format.setHorizontalAlignment(HorizontalAlignment.Justify);
        para_4.getFormat().setBeforeSpacing(20);

        Paragraph prorector = section.addParagraph();
        prorector.appendText("Руководитель учреждения образования\t_______________________\t\t"+ student.getGroup().getFaculty().getRector());
        prorector.applyStyle("Style1");

        prorector.getFormat().setBeforeAutoSpacing(false);
        prorector.getFormat().setBeforeSpacing(20);
        prorector.getFormat().setAfterAutoSpacing(false);
        prorector.getFormat().setAfterSpacing(20);


        Paragraph decan = section.addParagraph();
        decan.appendText("Декан факультета\t\t\t\t_______________________\t\t"+ student.getGroup().getFaculty().getDean());
        decan.applyStyle("Style1");

        Paragraph secretar = section.addParagraph();
        secretar.appendText("Секретарь факультета\t\t\t\t_______________________\t\t"+ student.getGroup().getFaculty().getSecretary());
        secretar.applyStyle("Style1");
        secretar.getFormat().setBeforeSpacing(20);
        secretar.getFormat().setAfterSpacing(20);

        Paragraph city = section.addParagraph();
        city.appendText(
                "Город Минск " + extractParamsDto.getDate());
        city.applyStyle("Style1");

        Paragraph reg_number = section.addParagraph();
        reg_number.appendText(
                "Регистрационный №: " + extractParamsDto.getRegistrationNumber());
        reg_number.applyStyle("Style1");

        document.saveToFile(
                "word-docs/"+student.getGroup().getNumber()+"_"+student.getLastName()+"_"+student.getFirstName()+"_"+studentId+".docx",
                FileFormat.Docx);

        return;
    }

    private String getMarkWithTextStr(String s) {
        switch (s) {
            case "4": return "4(четыре)";
            case "5": return "5(пять)";
            case "6": return "6(шесть)";
            case "7": return "7(семь)";
            case "8": return "8(восемь)";
            case "9": return "9(девять)";
            case "10": return "10(десять)";
        }
        return s;
    }

}
