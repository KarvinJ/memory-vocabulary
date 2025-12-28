package knight.nameless;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Memory extends ApplicationAdapter {

    public final int SCREEN_WIDTH = 420;
    public final int SCREEN_HEIGHT = 720;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    public OrthographicCamera camera;
    private ExtendViewport viewport;
    public Array<Texture> fruits;
    private int fruitIndex = 0;
    private boolean isAnswerHidden = true;

    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();

        FileHandle[] files = Gdx.files.local("img/fruits/").list();

        fruits = new Array<>();
        for(FileHandle file: files)
            fruits.add(new Texture(file.path()));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {

        Vector3 worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        var mouseBounds = new Rectangle(worldCoordinates.x, worldCoordinates.y, 2, 2);

        var hideBounds = new Rectangle(20, 320, SCREEN_WIDTH - 40, 50);
        var rightBounds = new Rectangle(20, 200, 100, 50);
        var leftBounds = new Rectangle(SCREEN_WIDTH - 120, 200, 100, 50);

        if (Gdx.input.justTouched() && mouseBounds.overlaps(rightBounds)) {

            fruitIndex++;

            if (fruitIndex >= fruits.size)
                fruitIndex = 0;
        }
        else if (Gdx.input.justTouched() && mouseBounds.overlaps(leftBounds)) {

            fruitIndex--;

            if (fruitIndex < 0)
                fruitIndex = fruits.size - 1;
        }

        else if (Gdx.input.justTouched() && mouseBounds.overlaps(hideBounds))
            isAnswerHidden = !isAnswerHidden;

        ScreenUtils.clear(Color.DARK_GRAY);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(fruits.get(fruitIndex), 10, SCREEN_HEIGHT / 2f - 50, 400, 400);
        batch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.WHITE);

        if (isAnswerHidden) {

            shapeRenderer.rect(
                hideBounds.x,
                hideBounds.y,
                hideBounds.width,
                hideBounds.height
            );
        }

        shapeRenderer.rect(
            rightBounds.x,
            rightBounds.y,
            rightBounds.width,
            rightBounds.height
        );

        shapeRenderer.rect(
            leftBounds.x,
            leftBounds.y,
            leftBounds.width,
            leftBounds.height
        );

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
