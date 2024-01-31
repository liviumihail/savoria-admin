package com.example.restaurantadmin.controller;

import com.example.restaurantadmin.service.ShoppingCartService;
import com.example.restaurantadmin.service.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    TableReservationService tableReservationService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String index2(Model model) {
        Long inStoreReservations = 0L;
        Long onlineReservations = 0L;
        if (tableReservationService.getInStoreReservations() != null) {
            inStoreReservations = tableReservationService.getInStoreReservations();
        }
        if (tableReservationService.getOnlineReservations() != null) {
            onlineReservations = tableReservationService.getOnlineReservations();
        }

        Long freeSeats = tableReservationService.getTotalSeats() - (onlineReservations + inStoreReservations);
        List<Long> listOfPercentage = tableReservationService.calculatePercentage(inStoreReservations, onlineReservations, freeSeats);
        //numere
        model.addAttribute("onlineReservationNumber", onlineReservations);
        model.addAttribute("inStoreReservationsNumber", inStoreReservations);
        model.addAttribute("remainingFreeSeatsNumber", freeSeats);

        //procente
        //locuri libere procente
        model.addAttribute("freeSeats", listOfPercentage.get(2));
//        model.addAttribute("freeSeats", 60);
        //locuri rezervate online
        model.addAttribute("onlineReservations", listOfPercentage.get(1));
//        model.addAttribute("onlineReservations", 20);
        //locuri rezervate in magazin
        model.addAttribute("inStoreReservations", listOfPercentage.get(0));
//        model.addAttribute("inStoreReservations", 20);

        //sales chart
        getMonthlyTotalPrices(model);

        return "index";
    }

    public void getMonthlyTotalPrices(Model model) {
        List<Map<String, Object>> monthlyTotals = shoppingCartService.getTotalPricesByMonth();

        // Now you can iterate through the list and access the data as needed
        int i = 1;
        for (Map<String, Object> row : monthlyTotals) {
            Date monthYear = (Date) row.get("month_year");
            BigDecimal totalPrice = (BigDecimal) row.get("total_price");
            model.addAttribute("sale" + i, totalPrice);
            model.addAttribute("month" + i, monthYear);
            i++;
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        Long inStoreReservations = 0L;
        Long onlineReservations = 0L;
        if (tableReservationService.getInStoreReservations() != null) {
            inStoreReservations = tableReservationService.getInStoreReservations();
        }
        if (tableReservationService.getOnlineReservations() != null) {
            onlineReservations = tableReservationService.getOnlineReservations();
        }

        Long freeSeats = tableReservationService.getTotalSeats() - (onlineReservations + inStoreReservations);
        List<Long> listOfPercentage = tableReservationService.calculatePercentage(inStoreReservations, onlineReservations, freeSeats);
        //numere
        model.addAttribute("onlineReservationNumber", onlineReservations);
        model.addAttribute("inStoreReservationsNumber", inStoreReservations);
        model.addAttribute("remainingFreeSeatsNumber", freeSeats);

        //procente
        //locuri libere procente
        model.addAttribute("freeSeats", listOfPercentage.get(2));
//        model.addAttribute("freeSeats", 60);
        //locuri rezervate online
        model.addAttribute("onlineReservations", listOfPercentage.get(1));
//        model.addAttribute("onlineReservations", 20);
        //locuri rezervate in magazin
        model.addAttribute("inStoreReservations", listOfPercentage.get(0));
//        model.addAttribute("inStoreReservations", 20);

        //sales chart
        getMonthlyTotalPrices(model);

        return "index";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "addProduct";
    }

    @GetMapping("/forms-advanced")
    public String formsAdvanced() {
        return "forms-advanced";
    }

    @GetMapping("/pages-404-withoutmenus")
    public String pages404WithoutMenus() {
        return "pages-404-withoutmenus";
    }

    @GetMapping("/pages-404")
    public String pages404() {
        return "pages-404";
    }

    @GetMapping("/layouts-scroll")
    public String layoutsScroll() {
        return "layouts-scroll";
    }

    @GetMapping("/ui-elements-grid-system")
    public String uiElementsGridSystem() {
        return "ui-elements-grid-system";
    }

    @GetMapping("/maps-vector")
    public String mapsVector() {
        return "maps-vector";
    }

    @GetMapping("/forms-layouts")
    public String formsLayouts() {
        return "forms-layouts";
    }

    @GetMapping("/mailbox-email")
    public String mailboxEmail() {
        return "mailbox-email";
    }

    @GetMapping("/pages-500")
    public String pages500() {
        return "pages-500";
    }

    @GetMapping("/pages-lock-screen-example")
    public String pagesLockScreenExample() {
        return "pages-lock-screen-example";
    }

    @GetMapping("/layouts-menu-collapsed")
    public String layoutsMenuCollapsed() {
        return "layouts-menu-collapsed";
    }

    @GetMapping("/pages-search-results")
    public String pagesSearchResults() {
        return "pages-search-results";
    }

    @GetMapping("/pages-session-timeout")
    public String pagesSessionTimeout() {
        return "pages-session-timeout";
    }

    @GetMapping("/forms-basic")
    public String formsBasic() {
        return "forms-basic";
    }

    @GetMapping("/ui-elements-animations")
    public String uiElementsAnimations() {
        return "ui-elements-animations";
    }

    @GetMapping("/pages-calendar")
    public String pagesCalendar() {
        return "pages-calendar";
    }

    @GetMapping("/tables-editable")
    public String tablesEditable() {
        return "tables-editable";
    }

    @GetMapping("/pages-invoice-print")
    public String pagesInvoicePrint() {
        return "pages-invoice-print";
    }

    @GetMapping("/mailbox-compose")
    public String mailboxCompose() {
        return "mailbox-compose";
    }

    @GetMapping("/ui-elements-portlets")
    public String uiElementsPortlets() {
        return "ui-elements-portlets";
    }

    @GetMapping("/ui-elements-notifications")
    public String uiElementsNotifications() {
        return "ui-elements-notifications";
    }

    @GetMapping("/ui-elements-accordions")
    public String uiElementsAccordions() {
        return "ui-elements-accordions";
    }

    @GetMapping("/ui-elements-tabs")
    public String uiElementsTabs() {
        return "ui-elements-tabs";
    }

    @GetMapping("/ui-elements-progressbars")
    public String uiElementsProgressbars() {
        return "ui-elements-progressbars";
    }

    @GetMapping("/forms-code-editor")
    public String formsCodeEditor() {
        return "forms-code-editor";
    }

    @GetMapping("/forms-validation")
    public String formsValidation() {
        return "forms-validation";
    }

    @GetMapping("/ui-elements-icons")
    public String uiElementsIcons() {
        return "ui-elements-icons";
    }

    @GetMapping("/ui-elements-alerts")
    public String uiElementsAlerts() {
        return "ui-elements-alerts";
    }

    @GetMapping("/ui-elements-buttons")
    public String uiElementsButtons() {
        return "ui-elements-buttons";
    }

    @GetMapping("/ui-elements-carousels")
    public String uiElementsCarousels() {
        return "ui-elements-carousels";
    }

    @GetMapping("/ui-elements-modals")
    public String uiElementsModals() {
        return "ui-elements-modals";
    }

    @GetMapping("/ui-elements-widgets")
    public String uiElementsWidgets() {
        return "ui-elements-widgets";
    }

    @GetMapping("/ui-elements-lightbox")
    public String uiElementsLightbox() {
        return "ui-elements-lightbox";
    }

    @GetMapping("/ui-elements-tree-view")
    public String uiElementsTreeView() {
        return "ui-elements-tree-view";
    }

    @GetMapping("/ui-elements-sliders")
    public String uiElementsSliders() {
        return "ui-elements-sliders";
    }

    @GetMapping("/ui-elements-charts")
    public String uiElementsCharts() {
        return "ui-elements-charts";
    }

    @GetMapping("/ui-elements-nestable")
    public String uiElementsNestable() {
        return "ui-elements-nestable";
    }

    @GetMapping("/ui-elements-extra")
    public String uiElementsExtra() {
        return "ui-elements-extra";
    }

    @GetMapping("/ui-elements-panels")
    public String uiElementsPanels() {
        return "ui-elements-panels";
    }

    @GetMapping("/ui-elements-blocks")
    public String uiElementsBlocks() {
        return "ui-elements-blocks";
    }

    @GetMapping("/tables-basic")
    public String tablesBasic() {
        return "tables-basic";
    }

    @GetMapping("/tables-responsive")
    public String tablesResponsive() {
        return "tables-responsive";
    }

    @GetMapping("/tables-ajax")
    public String tablesAjax() {
        return "tables-ajax";
    }

    @GetMapping("/tables-pricing")
    public String tablesPricing() {
        return "tables-pricing";
    }

    @GetMapping("/tables-advanced")
    public String tablesAdvanced() {
        return "tables-advanced";
    }

    @GetMapping("/pages-recover-password")
    public String recoverPassword() {
        return "pages-recover-password";
    }

    @GetMapping("/pages-user-profile")
    public String userProfile() {
        return "pages-user-profile";
    }

    @GetMapping("/pages-timeline")
    public String timeline() {
        return "pages-timeline";
    }

    @GetMapping("/pages-media-gallery")
    public String mediaGallery() {
        return "pages-media-gallery";
    }

    @GetMapping("/pages-blank")
    public String blank() {
        return "pages-blank";
    }

    @GetMapping("/pages-log-viewer")
    public String logViewer() {
        return "pages-log-viewer";
    }

    @GetMapping("/ui-elements-typography")
    public String typography() {
        return "ui-elements-typography";
    }

    @GetMapping("/mailbox-folder")
    public String mailboxFolder() {
        return "mailbox-folder";
    }

    @GetMapping("/pages-lock-screen")
    public String pagesLockScreen() {
        return "pages-lock-screen";
    }

    @GetMapping("/layouts-default")
    public String layoutsDefault() {
        return "layouts-default";
    }

    @GetMapping("/forms-wizard")
    public String formsWizard() {
        return "forms-wizard";
    }

    @GetMapping("/update-product")
    public String updateProducts() {
        return "update-product";
    }

    @GetMapping("/maps-google-maps")
    public String mapsGoogleMaps() {
        return "maps-google-maps";
    }

    @GetMapping("/maps-google-maps-builder")
    public String mapsGoogleMapsBuilder() {
        return "maps-google-maps-builder";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("termsAndConditions")
    public String termsAndConditions() {
        return "termsAndConditions";
    }

}
