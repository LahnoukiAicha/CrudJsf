package Beans;

import java.io.Serializable;
import java.util.Locale;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

@Named("localeBean")
@SessionScoped
public class LocalBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String locale;

    public String getLocal() {
        return locale;
    }

    public void setLocal(String locale) {
        this.locale = locale;
    }

    public void changeLocale(String newLocale) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocale));
    }
}