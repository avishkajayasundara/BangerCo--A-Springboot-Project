package com.eirlss.service;

import com.eirlss.dto.WebScrapVehicleClass;
import com.eirlss.dto.WebScrapVehicleDetails;
import com.eirlss.dto.WebScrapVehicleModel;
import com.eirlss.exception.GenericException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class WebScraperService {
    public WebScrapVehicleDetails getWebScrapedData() {
        WebScrapVehicleDetails webScrapVehicleDetails = null;
        try {
            Document doc = Jsoup.connect("https://www.amerirentacar.com/self-drive-rates-in-sri-lanka/").get();
            Element table = doc.select("tbody").first();
            webScrapVehicleDetails = new WebScrapVehicleDetails();
            List<WebScrapVehicleClass> webScrapVehicleClasses = new ArrayList<>();
            WebScrapVehicleClass webScrapVehicleClass = null;
            List<WebScrapVehicleModel> webScrapVehicleModels = new ArrayList<>();
            WebScrapVehicleModel webScrapVehicleModel = null;
            Elements tableRows = table.select("tr");
            for (int i = 0; i < tableRows.size(); i++) {
                Elements tableColumns = tableRows.get(i).select("td");
                for (int j = 0; j < tableColumns.size(); j++) {
                    if (isEmpty(tableColumns.get(j).text())) {
                        continue;
                    }
                    if (tableColumns.get(j).select("h2").size() > 0) {
                        if (nonNull(webScrapVehicleClass)) {
                            webScrapVehicleClass.setWebScrapVehicleModels(webScrapVehicleModels);
                            webScrapVehicleClasses.add(webScrapVehicleClass);
                            webScrapVehicleModels = new ArrayList<>();
                        }
                        webScrapVehicleClass = new WebScrapVehicleClass();
                        webScrapVehicleClass.setVechicleClass(tableColumns.get(j).select("h2").text());
                        continue;
                    }
                    if (j == 0) {
                        webScrapVehicleModel = new WebScrapVehicleModel();
                        webScrapVehicleModel.setVehicleModel(tableColumns.get(j).text());
                        continue;
                    }
                    if (j == 1) {
                        webScrapVehicleModel.setRatePerMonth(tableColumns.get(j).text());
                        continue;
                    }
                    if (j == 2) {
                        webScrapVehicleModel.setRatePerWeek(tableColumns.get(j).text());
                        continue;
                    }
                    if (j == 3) {
                        webScrapVehicleModel.setExcessMileage(tableColumns.get(j).text());
                        webScrapVehicleModels.add(webScrapVehicleModel);
                        continue;
                    }
                }
            }
            webScrapVehicleClass.setWebScrapVehicleModels(webScrapVehicleModels);
            webScrapVehicleClasses.add(webScrapVehicleClass);
            webScrapVehicleDetails.setWebScrapVehicleClasses(webScrapVehicleClasses);
        } catch (Exception e) {
            throw new GenericException("Some error Occured");
        }
        return webScrapVehicleDetails;
    }
}
