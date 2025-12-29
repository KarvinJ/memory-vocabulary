package knight.nameless;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

enum GameScreen {
    MENU, FRUITS, ANIMALS, NUMBERS, COLORS
}

public class memory extends ApplicationAdapter {

    public final int SCREEN_WIDTH = 420;
    public final int SCREEN_HEIGHT = 720;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    public OrthographicCamera camera;
    private ExtendViewport viewport;
    public Array<Texture> fruits;
    public Array<Texture> animals;
    public Array<Texture> numbers;
    public Array<Texture> colors;
    private int textureIndex = 0;
    private boolean isAnswerHidden = false;
    private Texture arrowTexture;
    private Texture checkTexture;
    private Texture fruitTexture;
    private Texture animalTexture;
    private Texture numberTexture;
    private Texture colorTexture;
    private int maxIndex = 0;
    private GameScreen gameScreen = GameScreen.MENU;

    @Override
    public void create() {

        camera = new OrthographicCamera();
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
        viewport = new ExtendViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        shapeRenderer = new ShapeRenderer();

        batch = new SpriteBatch();

        fruits = new Array<>();
        animals = new Array<>();
        numbers = new Array<>();
        colors = new Array<>();

        loadTextures();

        maxIndex = fruits.size - 1;

        arrowTexture = new Texture("img/icons/arrow.png");
        checkTexture = new Texture("img/icons/check.png");

        fruitTexture = new Texture("img/fruits/fruits.jpg");
        animalTexture = new Texture("img/animals/さる.jpg");
        colorTexture = new Texture("img/colors/色.jpg");
        numberTexture = new Texture("img/numbers/1.jpg");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void loadTextures() {

        String baseImagePath = "img/fruits/";
        String imageExtension = ".jpg";

        String[] fruitNames = new String[]{
            "coconut", "grapefruit", "kiwi", "lemon", "lime", "mango",
            "orange", "apple", "apricot", "avocado", "banana", "fruits",
            "grapes", "guava", "pear", "pineapple", "ume", "zakuro", "akafuzasuberry",
            "blackberry", "blueberry", "cherry", "cranberry", "gooseberry", "melon",
            "raspberry", "strawberry", "watermelon"
        };

        for (var filename : fruitNames) {

            String actualImagePath = baseImagePath + filename + imageExtension;

            fruits.add(new Texture(actualImagePath));
        }

        String[] animalNames = new String[]{
            "きりん", "さる", "アリクイ", "オセロット", "カバ", "クロコダイル",
            "ゴリラ", "シファカ", "シマウマ", "ジャガー", "ゾウ", "チーター",
            "トラ", "バク", "パンダ", "ヒョウ", "ホワイトタイガー", "ライオン", "レッサーパンダ"
        };

        baseImagePath = "img/animals/";

        for (var filename : animalNames) {

            String actualImagePath = baseImagePath + filename + imageExtension;

            animals.add(new Texture(actualImagePath));
        }

        String[] colorNames = new String[]{
            "あおい", "あかい", "きいろ", "くろい", "しろい", "ちゃいろ",
            "はいいろ", "みどり", "むらさき", "オレンジ"
        };

        baseImagePath = "img/colors/";

        for (var filename : colorNames) {

            String actualImagePath = baseImagePath + filename + imageExtension;

            colors.add(new Texture(actualImagePath));
        }

        String[] numberNames = new String[]{
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10"
        };

        baseImagePath = "img/numbers/";

        for (var filename : numberNames) {

            String actualImagePath = baseImagePath + filename + imageExtension;

            numbers.add(new Texture(actualImagePath));
        }
    }

    @Override
    public void render() {

        Vector3 worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        var mouseBounds = new Rectangle(worldCoordinates.x, worldCoordinates.y, 2, 2);

        ScreenUtils.clear(Color.DARK_GRAY);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (gameScreen == GameScreen.MENU) {

            var fruitsBounds = new Rectangle(10, SCREEN_HEIGHT - 210, 190, 190);
            var animalsBounds = new Rectangle(SCREEN_WIDTH - 200, SCREEN_HEIGHT - 210, 190, 190);
            var numbersBounds = new Rectangle(10, SCREEN_HEIGHT - 420, 190, 190);
            var colorsBounds = new Rectangle(SCREEN_WIDTH - 200, SCREEN_HEIGHT - 420, 190, 190);

            if (Gdx.input.justTouched() && mouseBounds.overlaps(fruitsBounds)) {

                gameScreen = GameScreen.FRUITS;
                maxIndex = fruits.size - 1;
                textureIndex = 0;
            }
            else if (Gdx.input.justTouched() && mouseBounds.overlaps(animalsBounds)) {

                gameScreen = GameScreen.ANIMALS;
                maxIndex = animals.size - 1;
                textureIndex = 0;
            }
            else if (Gdx.input.justTouched() && mouseBounds.overlaps(numbersBounds)) {

                gameScreen = GameScreen.NUMBERS;
                maxIndex = numbers.size - 1;
                textureIndex = 0;
            }
            else if (Gdx.input.justTouched() && mouseBounds.overlaps(colorsBounds)) {

                gameScreen = GameScreen.COLORS;
                maxIndex = colors.size - 1;
                textureIndex = 0;
            }

            batch.draw(fruitTexture, fruitsBounds.x, fruitsBounds.y, fruitsBounds.width, fruitsBounds.height);
            batch.draw(animalTexture, animalsBounds.x, animalsBounds.y, animalsBounds.width, animalsBounds.height);
            batch.draw(numberTexture, numbersBounds.x, numbersBounds.y, numbersBounds.width, numbersBounds.height);
            batch.draw(colorTexture, colorsBounds.x, colorsBounds.y, colorsBounds.width, colorsBounds.height);

        } else {

            if (gameScreen == GameScreen.FRUITS)
                batch.draw(fruits.get(textureIndex), 10, SCREEN_HEIGHT / 2f - 50, 400, 400);

            else if (gameScreen == GameScreen.ANIMALS)
                batch.draw(animals.get(textureIndex), 10, SCREEN_HEIGHT / 2f - 50, 400, 400);

            else if (gameScreen == GameScreen.NUMBERS)
                batch.draw(numbers.get(textureIndex), 10, SCREEN_HEIGHT / 2f - 50, 400, 400);

            else if (gameScreen == GameScreen.COLORS)
                batch.draw(colors.get(textureIndex), 10, SCREEN_HEIGHT / 2f - 50, 400, 400);

            var leftBounds = new Rectangle(20, 150, 128, 128);
            var rightBounds = new Rectangle(SCREEN_WIDTH - 148, 150, 128, 128);

            if (Gdx.input.justTouched() && mouseBounds.overlaps(rightBounds)) {

                textureIndex++;

                if (textureIndex > maxIndex)
                    textureIndex = 0;
            }
            else if (Gdx.input.justTouched() && mouseBounds.overlaps(leftBounds)) {

                textureIndex--;

                if (textureIndex < 0)
                    textureIndex = maxIndex;
            }

            batch.draw(arrowTexture, rightBounds.x, rightBounds.y, rightBounds.width, rightBounds.height);
            batch.draw(arrowTexture, leftBounds.x + 128, leftBounds.y, -leftBounds.width, leftBounds.height);
            batch.draw(arrowTexture, leftBounds.x + 128, leftBounds.y, -leftBounds.width, leftBounds.height);

            var checkBounds = new Rectangle(10, 10, 64, 64);

            if (Gdx.input.justTouched() && mouseBounds.overlaps(checkBounds))
                gameScreen = GameScreen.MENU;

            batch.draw(checkTexture, checkBounds.x, checkBounds.y, checkBounds.width, checkBounds.height);
        }

        batch.end();

        if (gameScreen != GameScreen.MENU) {

            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            shapeRenderer.setColor(Color.WHITE);

            var hideBounds = new Rectangle(20, 320, SCREEN_WIDTH - 40, 50);

            if (Gdx.input.justTouched() && mouseBounds.overlaps(hideBounds))
                isAnswerHidden = !isAnswerHidden;

            if (isAnswerHidden) {

                shapeRenderer.rect(
                    hideBounds.x,
                    hideBounds.y,
                    hideBounds.width,
                    hideBounds.height
                );
            }

            shapeRenderer.end();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
