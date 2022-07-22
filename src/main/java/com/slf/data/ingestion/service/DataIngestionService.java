package com.slf.data.ingestion.service;

import com.slf.data.ingestion.domain.Address;
import com.slf.data.ingestion.domain.Person;
import com.slf.data.ingestion.domain.Pet;
import com.slf.data.ingestion.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class DataIngestionService {

    @Autowired
    PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        try {
            FileInputStream file = new FileInputStream(new File("Persons.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            List<Person> persons = new ArrayList<>();
            List<Address> addresses = new ArrayList<>();
            List<Pet> pets = new ArrayList<>();
            int rowIndex = 0;
            int colIndex;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                //Starting from -1 because we skip the first one column
                //Index to get current person - line 74
                colIndex = -1;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    //If the column is the first or cell is empty - we skip it
                    if (colIndex == -1 || cell == null) {
                        colIndex++;
                        continue;
                    }

                    //Add a new person for each column, except the first one.
                    if (rowIndex == 0) {
                        persons.add(new Person());
                    }

                    var currentPerson = persons.get(colIndex);
                    //Check the cell type and format accordingly
                    switch (row.getCell(0).getStringCellValue()) {
                        case "Name":
                            currentPerson.setName(cell.getStringCellValue());
                            break;
                        case "Age":
                            currentPerson.setAge((int) cell.getNumericCellValue());
                            break;
                        case "Country":
                            //And new address any time we have country in Excel for current person
                            addresses.add(new Address());
                            currentPerson.addAddress(addresses.get(addresses.size() - 1));
                            currentPerson.getAddresses().get(currentPerson.getAddresses().size() - 1).setCountry(cell.getStringCellValue());
                            break;
                        case "Street":
                            //get last added address and attach street to him
                            currentPerson.getAddresses().get(currentPerson.getAddresses().size() - 1).setStreet(cell.getStringCellValue());
                            break;
                        case "City":
                            //get last added address and attach city to him
                            currentPerson.getAddresses().get(currentPerson.getAddresses().size() - 1).setCity(cell.getStringCellValue());
                            break;
                        case "PetName":
                            //And new pet any time we have petName in Excel for current person
                            pets.add(new Pet());
                            currentPerson.addPet(pets.get(pets.size() - 1));
                            currentPerson.getPets().get(currentPerson.getPets().size() - 1).setName(cell.getStringCellValue());
                            break;
                        case "PetAge":
                            //get last added pet and attach age to him
                            currentPerson.getPets().get(currentPerson.getPets().size() - 1).setAge((int) cell.getNumericCellValue());
                            break;
                        case "Species":
                            //get last added pet and attach species to him
                            currentPerson.getPets().get(currentPerson.getPets().size() - 1).setSpecies(cell.getStringCellValue());
                            break;
                    }
                    colIndex++;
                }
                rowIndex++;
            }
            file.close();

            persons = personRepository.saveAll(persons);
            log.info(persons.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
