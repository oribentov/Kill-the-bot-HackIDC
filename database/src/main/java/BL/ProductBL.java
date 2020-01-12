package BL;

import DAO.ProductDAO;
import Utilities.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;


import java.sql.SQLException;

public class ProductBL {

    private ProductDAO dao;

    public ProductBL() {
        this.dao = new ProductDAO();
    }

    public JsonNode GetProductCategories() throws SQLException {
       return JsonParser.ObjectToJson(dao.GetProductCategories());
    }

    public JsonNode GetProductSubCategories() throws SQLException {
        return JsonParser.ObjectToJson(dao.GetProductSubCategories());
    }

    public JsonNode GetProductTypes() throws SQLException {
        return JsonParser.ObjectToJson(dao.GetProductTypes());
    }

    public JsonNode GetProducts() throws SQLException {
        return JsonParser.ObjectToJson(dao.GetProducts());
    }
}
