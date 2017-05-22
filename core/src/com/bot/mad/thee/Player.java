package com.bot.mad.thee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
    private World myWorld = MyWorld.world;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private Body body;
    private float x, y;
    private float radius;

    private boolean isRed;

    private float startingX;
    private float startingY;
    private float MAX_VELOCITY = 3f;

    Player(OrthographicCamera camera) {
        this.camera = camera;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.ROYAL);

        isRed = false;

        x = startingX = 2;
        y = startingY = 6;
        radius = .48f; // 1 meter

        /* Create Body definition */
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = myWorld.createBody(bodyDef);

        // Create a circle shape and add a fixture to our shape
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 5f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        circle.dispose();

        setContactListeners();
    }

    public void draw() {
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x,y, radius);
        shapeRenderer.end();
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {
            MAX_VELOCITY = 3f;

            float pushX = 0.55f;
            float pushY = -2f;
            float impulseX = 0;
            float impulseY = pushY;

            Vector2 velocity = body.getLinearVelocity();
            impulseX = velocity.x+impulseX <= MAX_VELOCITY ? pushX : 0;

            body.applyLinearImpulse(impulseX, impulseY, x, y, true);
        }

        x = body.getPosition().x;
        y = body.getPosition().y;

        //camera.position.set(((x*delta)+(camera.viewportWidth/2)-5), (y*delta) + 1, 0);
        float smoothing = 0.5f; // lower the smoother
        camera.position.lerp(new Vector3(body.getPosition().x, body.getPosition().y, 0), smoothing);
        camera.update();
    }

    public void interpolate(float alpha) {
        Transform transform = body.getTransform();
        Vector2 bodyPosition = transform.getPosition();
        Vector2 position = body.getPosition();
        float angle = body.getAngle();
        float bodyAngle = transform.getRotation();

        //x = bodyPosition.x * alpha + position.x * (1.0f - alpha);
        y = bodyPosition.y * alpha + position.y * (1.0f - alpha);
    }

    public void reset() {
        body.setTransform(startingX, startingY, 0);
        body.setLinearVelocity(new Vector2(0,0));
        body.setAngularVelocity(0);
    }

    private void setContactListeners() {
        myWorld.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public Body getBody() { return body; }

    public float getX() { return x; }

    public float getY() { return y; }
}
