package knight.nameless;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Memory extends ApplicationAdapter {

    public final int SCREEN_WIDTH = 420;
    public final int SCREEN_HEIGHT = 720;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Texture image;
    public OrthographicCamera camera;
    public ExtendViewport viewport;

    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {

        ScreenUtils.clear(Color.BLACK);

//        batch.setProjectionMatrix(viewport.getCamera().combined);
//        batch.begin();
//        batch.draw(image, 0, 0, 32 , 32);
//        batch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.LIGHT_GRAY);

        shapeRenderer.rect(
            0,
            0,
            64,
            64
        );

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
