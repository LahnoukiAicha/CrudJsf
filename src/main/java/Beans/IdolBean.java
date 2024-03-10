package Beans;

import Model.Idol;
import Services.IdolService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class IdolBean implements Serializable {

    private List<Idol> idols;
    private Map<Integer, Boolean> editModes;
    private Idol idolAdd = new Idol();

    private final IdolService idolService = new IdolService();
    private String emailExistError;

    private boolean showCodeSnippet = false;



    @PostConstruct
    public void init() {
        refreshData();
    }

    public List<Idol> getIdols() {
        return idols;
    }

    public Idol getIdolAdd() {
        return idolAdd;
    }

    public void setIdolAdd(Idol idolAdd) {
        this.idolAdd = idolAdd;
    }

    public void addIdol() {
        idolService.addIdolService(idolAdd);
        refreshData();
        showCodeSnippet = false;

    }

    public void updateIdol(Idol idol) {
        idolService.updateIdolService(idol);
        refreshData();
    }

    public void deleteIdol(int id) {
        idolService.deleteIdolService(id);
        refreshData();
    }

    public Map<Integer, Boolean> getEditModes() {
        return editModes;
    }

    public void toggleEditMode(Idol idol) {
        int idolId = idol.getId();
        boolean currentMode = editModes.get(idolId);
        editModes.put(idolId, !currentMode);
    }

    public void refreshData() {
        idols = idolService.getIdolsListService();
        editModes = new HashMap<>();
        for (Idol idol : idols) editModes.put(idol.getId(), false);

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("idolTableForm:idolTable");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Table refreshed successfully.", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String getEmailExistError() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = context.getMessageList("email").stream()
                .filter(m -> m.getSeverity() == FacesMessage.SEVERITY_ERROR)
                .findFirst().orElse(null);
        return message != null ? message.getSummary() : null;
    }

    public String getRequiredError() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = context.getMessageList("email").stream()
                .filter(m -> m.getSeverity() == FacesMessage.SEVERITY_ERROR && m.getDetail().contains("Email validation failed"))
                .findFirst().orElse(null);
        return message != null ? message.getSummary() : null;
    }

    public void showCode() {
        showCodeSnippet = true;
    }

    public boolean isShowCodeSnippet() {
        return showCodeSnippet;
    }

    public void setShowCodeSnippet(boolean showCodeSnippet) {
        this.showCodeSnippet = showCodeSnippet;
    }
}
