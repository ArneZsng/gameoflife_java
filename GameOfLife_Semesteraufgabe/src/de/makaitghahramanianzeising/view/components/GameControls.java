package de.makaitghahramanianzeising.view.components;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

import de.makaitghahramanianzeising.model.Game;

public class GameControls extends Composite {
    private static int controlsHeight = 50;
    private Button btnNewGame;
    private Game game;
    private Label lblRound;
    private Label lblSpeed;
    private Slider sldSpeed;

    public GameControls(Shell shell, Game game) {
        super(shell, SWT.NULL);
        this.game = game;
        setLayout(new GridLayout(10, false));
        GridData gdControlsComposite = new GridData();
        gdControlsComposite.heightHint = controlsHeight;
        setLayoutData(gdControlsComposite);
        initRoundLabel();
        initSpeedAdjustment();
        initNewGameButton();
    }

    private void initRoundLabel() {
        lblRound = new Label(this, SWT.NONE);
        setRoundLabel(game.getRoundAsString());
        GridData gdRoundLabel = new GridData();
        gdRoundLabel.widthHint = 200;
        lblRound.setLayoutData(gdRoundLabel);
    }

    public void setRoundLabel(String round) {
        lblRound.setText("Generation: " + round);
    }

    private void initSpeedAdjustment() {
        Label lblSpeedDesc = new Label(this, SWT.NONE);

        Label lbl0 = new Label(this, SWT.NONE);
        lbl0.setText("0");

        sldSpeed = new Slider(this, SWT.HORIZONTAL);
        sldSpeed.setValues(game.getSpeed(), 0, 1001, 1, 50, 50);

        Label lbl1 = new Label(this, SWT.NONE);
        lbl1.setText("1");

        lblSpeedDesc.setText("Spielgeschwindigkeit:");
        lblSpeed = new Label(this, SWT.NONE);
        lblSpeed.setText(String.valueOf(((double) getSpeed()) / 1000));

        GridData gdSpeedLabel = new GridData();
        gdSpeedLabel.widthHint = 100;
        lblSpeed.setLayoutData(gdSpeedLabel);
    }

    private void initNewGameButton() {
        btnNewGame = new Button(this, SWT.NONE);
        btnNewGame.setText("Neues Spiel");
    }

    public void setSpeed() {
        String speed = String.valueOf((double) (getSpeed()) / 1000);
        lblSpeed.setText(speed);
    }

    public int getSpeed() {
        return sldSpeed.getSelection();
    }

    public void addNewGameListener(SelectionAdapter listenForNewGameButton) {
        btnNewGame.addSelectionListener(listenForNewGameButton);
    }


    public void addSpeedSliderListener(SelectionAdapter listenForSpeedSlider) {
        sldSpeed.addSelectionListener(listenForSpeedSlider);
    }
}
