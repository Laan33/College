using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameManagerScript : MonoBehaviour
{
    public GameObject marsObject;
    public GameObject phobosObject;
    public GameObject deimosObject;
    // Start is called before the first frame update
    void Start()
    {
        marsObject.transform.position = new Vector3(0,0,0);
        Camera.main.transform.position = new Vector3(0,0,150);
        Camera.main.transform.LookAt(marsObject.transform);
    }

    // Update is called once per frame
    void Update()
    {
        marsObject.transform.Rotate(new Vector3(0,10*Time.deltaTime,0));
     //rotate Phobos and Deimos objects around Mars a little on each frame (use e.g. phobos.transform.RotateAround)
        phobosObject.transform.RotateAround(marsObject.transform.position, Vector3.up, 10 * Time.deltaTime);
        deimosObject.transform.RotateAround(marsObject.transform.position, Vector3.up, 10 * Time.deltaTime);



        //rotate the camera around Mars when the user presses the left and right arrow keys (use e.g. Camera.main.transform.RotateAround)
        //and up and down to pan up and down
        if (Input.GetKey(KeyCode.LeftArrow))
        {
            Camera.main.transform.RotateAround(marsObject.transform.position, Vector3.up, 10 * Time.deltaTime);
        }
        if (Input.GetKey(KeyCode.RightArrow))
        {
            Camera.main.transform.RotateAround(marsObject.transform.position, Vector3.down, 10 * Time.deltaTime);
        } 
        if (Input.GetKey(KeyCode.UpArrow))
        {
            Camera.main.transform.RotateAround(marsObject.transform.position, Vector3.right, 10 * Time.deltaTime);
        }
        if (Input.GetKey(KeyCode.DownArrow))
        {
            Camera.main.transform.RotateAround(marsObject.transform.position, Vector3.left, 10 * Time.deltaTime);
        }
   
    }

}