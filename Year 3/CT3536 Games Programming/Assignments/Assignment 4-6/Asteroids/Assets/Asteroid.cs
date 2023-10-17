using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Asteroid : MonoBehaviour
{
    public GameObject asteroidObject, spaceshipPrefab, smallAsteroidPrefab, bulletPrefab;
    private Vector3 spawnPoint;
    private bool ignoreCollisions = true;

    // Start - called before the first frame update
    void Start()
    {
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
            GameObject smallAsteroid = GameObject.Instantiate(smallAsteroidPrefab);
            //Setting position to the collision point and scaling it down
            smallAsteroid.transform.position = collisionPoint;
            smallAsteroid.transform.localScale = new Vector3(0.01f, 0.01f, 0.01f);
            //Adding a random force and torque to the small asteroids
            smallAsteroid.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-100f, 100f), 0, Random.Range(-100f, 100f)));
            smallAsteroid.GetComponent<Rigidbody>().AddTorque(new Vector3(Random.Range(-100f, 100f), Random.Range(-100f, 100f), Random.Range(-100f, 100f)));
            //Destroying the small asteroids after 1.5 seconds
            Destroy(smallAsteroid, 1.5f);
        }
    }

    void SpawnSmallerAsteroids(Vector3 collisionPoint) {
        //Spawn between 3-4 small asteroids at the point of collision

        Log.Debug("SpawnSmallerAsteroids called");
        for (int i = 0; i < Random.Range(3, 5); i++)
        {
            GameObject smallAsteroid = GameObject.Instantiate(smallAsteroidPrefab);
            //Setting position to the collision point and scaling it down
            smallAsteroid.transform.position = collisionPoint;
            smallAsteroid.transform.localScale = new Vector3(0.01f, 0.01f, 0.01f);
            //Adding a random force and torque to the small asteroids
            smallAsteroid.GetComponent<Rigidbody>().AddForce(new Vector3(Random.Range(-100f, 100f), 0, Random.Range(-100f, 100f)));
            smallAsteroid.GetComponent<Rigidbody>().AddTorque(new Vector3(Random.Range(-100f, 100f), Random.Range(-100f, 100f), Random.Range(-100f, 100f)));
        }
    }

    /*Method for calling SpawnCollisionDebris on collisions */
    void OnCollisionEnter(Collision collision)
    {

        
        Debug.Log("Collision object is: " + collision.gameObject.name);


        //Checking if it is on spawn in, and if so, ignore collisions
        if (ignoreCollisions)
        {
            return;
        }

        switch (collision.gameObject.name)
        {
            case "Bullet(Clone)":
                //Calling SpawnCollisionDebris with the point of collision
                SpawnCollisionDebris(collision.contacts[0].point, 3F);
                //Destroying the bullet
                Destroy(collision.gameObject);
                //Destroying the asteroid
                Destroy(asteroidObject);

                if (asteroidObject.transform.localScale.x > 0.1f)
                {
                    //Destroying the asteroid
                    SpawnSmallerAsteroids(collision.contacts[0].point);
                }
                break;
            case "spacefighter":
                //Destroying the player
                Destroy(collision.gameObject.transform.parent.gameObject);
                break;
            case "Asteroid(Clone)":
                SpawnCollisionDebris(collision.contacts[0].point, 1.5F);
                break;
            default:
                break;
        }


        /*
        Debug.Log("collision with " + collision.gameObject.tag + " detected");
        Debug.Log("Collision object is: " + collision.gameObject.name);
        //If asteroid collides with player, destroy player & recreate player spaceship in middle of screen
        if (collision.gameObject.name == "spacefighter")
        {
            //Destroying the player
            Destroy(collision.gameObject.transform.parent.gameObject);
            Destroy(gameObject);
        } */

   
    }


}


/*
        //If bullet hits asteroid and the asteroid is large, split asteroid into 3 smaller asteroids
        //Destroy bullet and large asteroid
        if (collision.gameObject.tag == "Bullet" && asteroidObject.transform.localScale.x > 0.1f)
        {
            //Calling SpawnCollisionDebris with the point of collision
            SpawnCollisionDebris(collision.contacts[0].point);
            //Destroying the bullet
            Destroy(collision.gameObject);
            //Destroying the asteroid
            Destroy(asteroidObject);
        } else if (collision.gameObject.tag == "Bullet" && asteroidObject.transform.localScale.x <= 0.1f)
        {
            //Calling SpawnCollisionDebris with the point of collision
            SpawnCollisionDebris(collision.contacts[0].point);
            //Destroying the bullet
            Destroy(collision.gameObject);
            //Destroying the asteroid
            Destroy(asteroidObject);
        } */






     
