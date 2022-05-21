package com.kodilla.project;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CreateHTML {

    public void createHTMLFile(){

        for (Map.Entry<String, LoadInformations> entry: DataBaseStorage.resultMap.entrySet()) {

            if (entry.getValue().createdHMTL == false) {

                File file = new File(entry.getValue().getLicencePlate() + "_" + entry.getKey() + ".htm");
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.write("<style type=\"text/css\">\n" +
                            ".tg  {border-collapse:collapse;border-spacing:0;}\n" +
                            ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                            "  overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                            ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;\n" +
                            "  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}\n" +
                            ".tg .tg-0lax{text-align:left;vertical-align:top}\n" +
                            "</style>\n" +
                            "<table class=\"tg\">\n" +
                            "<thead>\n" +
                            "  <tr>\n" +
                            "    <th class=\"tg-0lax\">Załadunek</th>\n" +
                            "    <th class=\"tg-0lax\">Miejsce załadunku 1</th>\n" +
                            "  </tr>\n" +
                            "</thead>\n" +
                            "<tbody>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-0lax\">Rozładunek</td>\n" +
                            "    <td class=\"tg-0lax\">Klient 1 </td>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-0lax\">Waga na pusto<br></td>\n" +
                            "    <td class=\"tg-0lax\">" + entry.getValue().getTare() + " kg</td>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-0lax\">Waga po załadowaniu</td>\n" +
                            "    <td class=\"tg-0lax\"> " + entry.getValue().getGrossWeight() + " kg </td>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-0lax\">Załadowano: </td>\n" +
                            "    <td class=\"tg-0lax\"> " + (entry.getValue().getGrossWeight() - entry.getValue().getTare()) + " kg </td>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <td class=\"tg-0lax\">Podpis</td>\n" +
                            "    <td class=\"tg-0lax\"></td>\n" +
                            "  </tr>\n" +
                            "</tbody>\n" +
                            "</table>");
                    bufferedWriter.close();
                    entry.getValue().setCreatedHMTL(true);

                    Desktop.getDesktop().browse(file.toURI());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
