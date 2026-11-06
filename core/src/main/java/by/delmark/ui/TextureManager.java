package by.delmark.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureManager {

    private TextureRegionDrawable baseDrawable;
    private Texture pixelTexture;

    // TextureAtlas мб при росте текстур, либо костылять свою мапу
    private Texture calcBtnIcon;
    private Texture graphBtnIcon;

    public TextureRegionDrawable getBaseDrawable() {
        return baseDrawable;
    }

    public void loadTextures() {
        Pixmap pixel = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixel.setColor(Color.WHITE);

        pixel.fill();
        pixelTexture = new Texture(pixel);
        pixel.dispose();

        baseDrawable = new TextureRegionDrawable(pixelTexture);

        calcBtnIcon = new Texture("calc_icon.png");
        graphBtnIcon = new Texture("graph_icon.png");
    }

    public Texture getCalcBtnIcon() {
        return calcBtnIcon;
    }

    public Texture getGraphBtnIcon() {
        return graphBtnIcon;
    }

    public void clearTextures() {
        pixelTexture.dispose();
        calcBtnIcon.dispose();
        graphBtnIcon.dispose();
    }
}
