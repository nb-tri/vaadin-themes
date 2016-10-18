package org.apache.vaadin.admin.ui.layout;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.apache.vaadin.admin.ui.partials.FooterUI;
import org.apache.vaadin.admin.ui.partials.HeaderUI;
import org.apache.vaadin.admin.ui.partials.SidebarUI;

/**
 * Created by tringuyen on 10/17/16.
 */
public abstract class AdminLayoutUI extends UI {

    protected Navigator navigator;

    protected HeaderUI headerUI = new HeaderUI();
    protected FooterUI footerUI = new FooterUI();
    protected SidebarUI sidebarUI = new SidebarUI();

    protected abstract VerticalLayout drawMainLayout(VaadinRequest vaadinRequest);

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);

        VerticalLayout content = new VerticalLayout();

        HorizontalLayout body = new HorizontalLayout();

        Layout sidebar = sidebarUI.draw(request, navigator);
        Layout main = drawMainLayout(request);
        body.addComponents(sidebar, main);
        body.setExpandRatio(sidebar, 0.3f);
        body.setExpandRatio(main, 0.7f);

        Layout header = headerUI.draw(request);
        Layout footer = footerUI.draw(request);

        content.addComponents(header, body, footer);

        content.setMargin(true);
        content.setSpacing(true);

        setContent(content);
    }

    @Override
    public String getStyleName() {
        return ValoTheme.LAYOUT_COMPONENT_GROUP;
    }
}