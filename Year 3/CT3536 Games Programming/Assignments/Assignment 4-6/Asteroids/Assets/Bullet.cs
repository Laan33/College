using System.Collections;
using System.Collections.Generic;
using UnityEngine;

/// This class represents the behavior of a bullet in the game. It checks if the bullet is offscreen every 0.2 seconds and destroys it if so.

public class Bullet : MonoBehaviour
{
    public GameObject bullet;
    public GameObject spaceship;

    void Start()
    {
        //Check if bullet is offscreen every 0.2 seconds - destroy if so
        InvokeRepeating("DestroyIfOffScreen", 0.2f, 0.2f);
    }


    void DestroyIfOffScreen()
    {
        Vector3 pos = transform.position;
        Vector3 vel = GetComponent<Rigidbody>().velocity;
        //if offscreen, destroy bullet
        if ((pos.x > GameManager.screenTopRight.x && vel.x >= 0f)
        || (pos.x > GameManager.screenTopRight.x && vel.x >= 0f)
        || (pos.z < GameManager.screenBottomLeft.z && vel.z <= 0f)
        || (pos.z > GameManager.screenTopRight.z && vel.z >= 0f))
        {
            Destroy(bullet);
        }
    }
}
