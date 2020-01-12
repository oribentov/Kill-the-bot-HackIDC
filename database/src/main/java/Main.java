import BL.ProductBL;
import DAO.ProductDAO;
import BL.UserBL;
import DAO.FirstDAO;
import Models.User;
import RH.ProductResultBulder;
import RH.UserHandler;
import Utilities.WinnerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Spark;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args)  {
        InitConfigurationsRestAPI();

        generateRoutes();
    }

    private static void InitConfigurationsRestAPI() {
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Headers", "*");
            res.type("application/json");
        });
    }

    private static void InitStubRestAPI() {
        get("/helloa", (req, res) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString("Asd");
            return json;
        });
    }

    public static void GetExistUser() {
        get("/gerUser", (req, res) -> {
            String userId = String.valueOf(req.params().get("userId"));
            String password = String.valueOf(req.params().get("password"));
            // Get all users from lottery
            UserBL bl = new UserBL();
            User details = bl.GetUserDetails(userId, password);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(details);
            return json;
        });
    }



    public static void generateRoutes() {
        InitStubRestAPI();

        Spark.path("/api", () -> {

            /* WINNERS API */
            Spark.path("/winners", () -> {
                InitWinnerRestAPI();
            });

            /* PRODUCTS API */
            Spark.path("/products", () -> {
                ProductAPI();
            });


            Spark.post("/try", (req, res) -> {
                 ProductResultBulder.Try(req, res);
                 return null;
            });

            Spark.path("/sign-up", () ->{
                SignUp();
            });
        });
    }

    private static void SignUp() {
        UserHandler userHandler = new UserHandler();
        get("/sign-up", (req, res) -> userHandler.insertUser(req));
    }

    private static void InitWinnerRestAPI() {
        get("/getWinner", (req, res) -> {
            Integer lotteryId = Integer.valueOf(req.params().get("lotteryId"));

            // Get all users from lottery
            List<User> usersList = new ArrayList<>();
            User winner = WinnerUtil.getRandomWinner(usersList);

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(winner);
            return json;
        });
    }


    private static void ProductAPI() {
        ProductBL bl = new ProductBL();

        get("/getProductCategories", (req, res) -> bl.GetProductCategories());

        get("/getProductSubCategories", (req, res) -> bl.GetProductSubCategories());

        get("/getProductTypes", (req, res) -> bl.GetProductTypes());

        get("/getProducts", (req, res) -> bl.GetProducts());
    }
}