package com.novbank.ndp.kernel.vocabulary;

import com.novbank.ndp.kernel.rdfsupport.RDFResource;
import com.novbank.ndp.kernel.rdfsupport.RDFVocabulary;

/**
 * Created by CaoKe on 2015/5/12.
 */
public class DCTypes extends RDFVocabulary {
    public static final String NAMESPACE = "http://purl.org/dc/dcmitype/";
    public static final String PREFIX = "dcmitype";
    public static final DCTypes INSTANCE = new DCTypes();

    public final RDFResource Collection;
    public final RDFResource Dataset;
    public final RDFResource Event;
    public final RDFResource Image;
    public final RDFResource InteractiveResource;
    public final RDFResource MovingImage;
    public final RDFResource PhysicalObject;
    public final RDFResource Service;
    public final RDFResource Software;
    public final RDFResource Sound;
    public final RDFResource StillImage;
    public final RDFResource Text;
    
    public DCTypes() {
        super(NAMESPACE, PREFIX);
        Collection = addClass("collection");
        Dataset = addClass("Dataset");
        Event = addClass("Event");
        Image = addClass("Image");
        InteractiveResource = addClass("InteractiveResource");
        MovingImage = addClass("MovingImage");
        PhysicalObject = addClass("PhysicalObject");
        Service = addClass("Service");
        Software = addClass("Software");
        Sound = addClass("Sound");
        StillImage = addClass("StillImage");
        Text = addClass("Text");
        lock();
    }

    public static final RDFResource COLLECTION = INSTANCE.Collection;
    public static final RDFResource DATASET = INSTANCE.Dataset;
    public static final RDFResource EVENT = INSTANCE.Event;
    public static final RDFResource IMAGE = INSTANCE.Image;
    public static final RDFResource INTERACTIVE_RESOURCE = INSTANCE.InteractiveResource;
    public static final RDFResource MOVING_IMAGE = INSTANCE.MovingImage;
    public static final RDFResource PHYSICAL_OBJECT = INSTANCE.PhysicalObject;
    public static final RDFResource SERVICE = INSTANCE.Service;
    public static final RDFResource SOFTWARE = INSTANCE.Software;
    public static final RDFResource SOUND = INSTANCE.Sound;
    public static final RDFResource STILL_IMAGE = INSTANCE.StillImage;
    public static final RDFResource TEXT = INSTANCE.Text;
}
