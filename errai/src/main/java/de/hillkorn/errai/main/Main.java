package de.hillkorn.errai.main;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.jboss.errai.ui.nav.client.local.DefaultPage;
import org.jboss.errai.ui.nav.client.local.Page;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 *
 * @author hillkorn
 */
@Page(role = DefaultPage.class)
@Templated("Main.html#main")
public class Main extends Composite{
    
    @Inject
    @DataField
    Button helloButton;
    
    @PostConstruct
    public void init() {
        Window.alert("Main Page");
    }
}
