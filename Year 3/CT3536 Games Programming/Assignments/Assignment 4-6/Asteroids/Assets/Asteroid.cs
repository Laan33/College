using System.Collections;
using System.Collections.Generic;
using UnityEngine;


/// Represents an asteroid in the game. Handles spawning, movement, collision detection, and destruction.

public class Asteroid : MonoBehaviour
{
    public GameObject asteroidObject, spaceshipPrefab, AsteroidFragment, bulletPrefab;
    private Vector3 spawnPoint;
    private bool ignoreCollisions = true;

    // Start - called before the first frame update
    void Start()
    {
        //Set tag to Asteroid
        asteroidObject.tag = "Asteroid";

        //check the asteroids scale - if it's a small asteroid don't have it spawn at the edge of the screen
        if(asteroidObject.transform.localScale.x > 0.1f)
        {
            Invoke("DisableCollisionIgnore", 0.1f);
            return;
        }

        //Set the asteroid's position at a random position near the edges of the screen
        if (Random.Range(0, 2) == 0)
        {
            //Spawn on top or bottom
            if (Random.Range(0, 2) == 0)
            {
                //Spawn on top
                spawnPoint = new Vector3(Random.Range(-30f, 30f), 0, 30);
            }
            else
            {
                //Spawn on bottom
                spawnPoint = new Vector3(Random.Range(-30f, 30f), 0, -30);
            }
        }
        else
        {
            //Spawn on left or right
            if (Random.Range(0, 2) == 0)
            {
                //Spawn on left
                spawnPoint = new Vector3(-30, 0, Random.Range(-30f, 30f));
            }
            else
            {
                //Spawn on right
                spawnPoint = new Vector3(30, 0, Random.Range(-30f, 30f));
            }
        }

        //Set the asteroid's position
        asteroidObject.transform.position = spawnPoint;

        //Move the asteroid in a random direction
        asteroidObject.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-700f, 700f), 0, Random.Range(-700f, 700f)));

        //Rotate the asteroid in a random direction
        asteroidObject.GetComponent<Rigidbody>().AddTorque(new Vector3(Random.Range(-500f, 500f), Random.Range(-500f, 500f), Random.Range(-500f, 500f)));



        //This is a method that disables collisions for a tenth of a second at spawn in, in order to prevent not valid collisions
        Invoke("DisableCollisionIgnore", 0.1f);
    }

    void DisableCollisionIgnore()
    {
        //Disabling collision ignore boolean
        ignoreCollisions = false;
    }



    /*Each time an asteroid collides with something, spawn a few of the tiny asteroid prefabs at the point of
impact. They should be destroyed shortly afterwards. */

    void SpawnCollisionDebris(Vector3 collisionPoint, float multiplier)
    {
        //Spawn 3 small asteroids at the point of collision
        for (int i = 0; i < 3 * multiplier; i++)
        {
            GameObject smallAsteroid = GameObject.Instantiate(AsteroidFragment);
            //Setting position to the collision point with some variance and scaling it down
            smallAsteroid.transform.position = new Vector3(
                collisionPoint.x + Random.Range(-0.5f, 0.5f),
                collisionPoint.y + Random.Range(-0.5f, 0.5f),
                collisionPoint.z + Random.Range(-0.5f, 0.5f)
            );
            smallAsteroid.transform.localScale = new Vector3(0.01f, 0.01f, 0.01f);
            //Adding a random force and torque to the small asteroids
            smallAsteroid.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-30f, 30f), 0, Random.Range(-30f, 30f)));
            smallAsteroid.GetComponent<Rigidbody>().AddTorque(new Vector3(Random.Range(-30f, 30f), Random.Range(-30f, 30f), Random.Range(-30f, 30f)));
        }
    }
    void SpawnSmallerAsteroids(Vector3 collisionPoint)
    {
        //Spawn between 3-4 small asteroids at the point of collision
        Debug.Log("SpawnSmallerAsteroids called");
        for (int i = 0; i < Random.Range(3, 5); i++)
        {
            GameObject asteroid = Instantiate(Resources.Load("Asteroid", typeof(GameObject))) as GameObject;


            //Setting position to the collision point and scaling it down
            asteroid.transform.position = collisionPoint;

            asteroid.transform.localScale = new Vector3(Random.Range(0.01f, 0.06f), Random.Range(0.01f, 0.06f), Random.Range(0.01f, 0.06f));
            //Adding a random force and torque to the small asteroids
            asteroid.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-100f, 100f), 0, Random.Range(-100f, 100f)));
            asteroid.GetComponent<Rigidbody>().AddTorque(new Vector3(Random.Range(-100f, 100f), Random.Range(-100f, 100f), Random.Range(-100f, 100f)));

        }
    }

    /*Method for calling SpawnCollisionDebris on collisions */
    void OnCollisionEnter(Collision collision)
    {
        if (ignoreCollisions)
        {
            return;
        }

        Debug.Log("Collision object is: " + collision.gameObject.tag);
        switch (collision.gameObject.tag)
        {
            case "Bullet":
                //Calling SpawnCollisionDebris with the point of collision
                SpawnCollisionDebris(collision.contacts[0].point, 3F);
                //Destroying the bullet
                Destroy(collision.gameObject);
                //Destroying the asteroid
                Destroy(asteroidObject);

                if (asteroidObject.transform.localScale.x > 0.1f)
                {
                    //Add points to score
                    GameManager.instance.AddScore(12);

                    SpawnCollisionDebris(collision.contacts[0].point, 1F); //extra debris for larger asteroids (also fun)
                    //Destroying the asteroid
                    SpawnSmallerAsteroids(collision.contacts[0].point);
                }
                else if (asteroidObject.transform.localScale.x > 0.05F)
                {
                    //Add points to score
                    GameManager.instance.AddScore(7);

                    SpawnCollisionDebris(collision.contacts[0].point, 2F); //extra debris for larger asteroids (also fun)
                }
                break;
            case "SpaceShip":
                //Destroy & respawn spaceship handled in spaceship script - return
                break;
            case "Asteroid":
                SpawnCollisionDebris(collision.contacts[0].point, 1.5F);
                break;
            default:
                break;
        }
    }


}

