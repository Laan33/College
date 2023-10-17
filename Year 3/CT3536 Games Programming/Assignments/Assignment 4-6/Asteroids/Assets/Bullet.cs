using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour
{
    public GameObject bullet;
    public GameObject spaceship;


    // Start is called before the first frame update
    void Start()
    {
        //Check if bullet is offscreen every 0.2 seconds - destroy if so
        InvokeRepeating("DestroyIfOffScreen", 0.2f, 0.2f);

    }

    // Update is called once per frame
    void Update()
    {


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
        /*
        Vector3 currentWorldPos = spaceship.transform.position;
                Vector3 viewPosition = Camera.main.WorldToViewportPoint(currentWorldPos);

        */
    }


}
