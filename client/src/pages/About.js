import React from "react";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import Dragon from "../static/images/debugdragon.png";
import { Grid } from "@mui/material";

export default function About() {
    return (
        <Card>
            <Grid container spacing={1}>
                <Grid item xs={12} md={3}>
                    <CardMedia
                        component="img"
                        image={Dragon}
                        alt="green dragon"
                    />
                </Grid>
                <Grid item xs={12} md={9}>
                    <Typography sx={{ p: 1 }} variant="body1">
                        Dungeons and Debugger's mission will be to develop the
                        project in an organized, structured manner while seeking
                        to help everyone on the team. No team member should be
                        left without assistance if they are struggling with
                        their portion of the tasks. As a result, we will refine
                        everyone's skill sets, and create a cohesive product. We
                        hope to make communication one of the strongest aspects
                        of our team. Every team member should have a good
                        understanding of every part of the project. We strive to
                        have structured and productive scrums at the start of
                        each class period where we update each other on
                        individual progress. Throughout this semester, our team
                        will be defined by our excellent communication, work
                        ethic, and due diligence.
                    </Typography>
                </Grid>
            </Grid>
        </Card>
    );
}
